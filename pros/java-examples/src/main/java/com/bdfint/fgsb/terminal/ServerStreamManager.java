package com.bdfint.fgsb.terminal;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public final class ServerStreamManager {

   // private static final String TAG = "ServerStreamManager";
    private static final Logger sLogger =
            Logger.getLogger(ServerStreamManager.class.getName());

    private final ReqProcessor mProcessor;
    private final Scheduler mScheduler;
    private ExceptionProcessor mExceptionProcessor;

    private final List<UserSpace> mUsers = new ArrayList<>();
    private Server mServer;

    public ServerStreamManager(Scheduler scheduler, ReqProcessor mProcessor) {
        this.mScheduler = scheduler;
        this.mProcessor = mProcessor;
    }
    /**
     * start server with target the port. must called in single thread.
     * @param port the port to listen
     * @throws IOException if already started or shut down
     * IOException if unable to bind
     * @throws InterruptedException if trigger interrupt
     */
    public void start(int port) throws IOException, InterruptedException {
        final Server server = ServerBuilder
                .forPort(port)
                .addService(new IOStreamingImpl(this))
                .build()
                .start();
        mServer = server;

        sLogger.info("Listening on " + server.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("Shutting down");
                try {
                    end();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        server.awaitTermination();
        sLogger.info("Server end. awaitTermination ");
    }
    public void end() throws InterruptedException{
        ArrayList<UserSpace> userSpaces = new ArrayList<>(mUsers);
        synchronized (mUsers){
            mUsers.clear();
        }
        for (UserSpace us: userSpaces){
            us.end();
        }
        if(mServer != null){
            mServer.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            mServer = null;
            sLogger.info("Server end. awaitTermination ");
        }
    }
    public void addMessage(ResMessage msg){
        mScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                sendMessage(msg);
            }
        });
    }
    /**
     * send message directly. must called in sub-thread.
     * @param msg the message.
     */
    public void sendMessage(ResMessage msg){
        for (UserSpace us: new ArrayList<>(mUsers)){
            us.sendMessage(msg);
        }
    }
    public void setExceptionProcessor(ExceptionProcessor mExceptionProcessor) {
        this.mExceptionProcessor = mExceptionProcessor;
    }

    private UserSpace newUserSpace(){
        UserSpace userSpace = new UserSpace();
        userSpace.setUp(mProcessor, mScheduler, mExceptionProcessor);
        userSpace.mParent = this;
        synchronized (mUsers){
            mUsers.add(userSpace);
        }
        return userSpace;
    }
    public static class UserSpace{
        private final AtomicBoolean mCanceled = new AtomicBoolean(false);
        private ReqProcessor mProcessor;
        private Scheduler mScheduler;
        private ExceptionProcessor mExceptionProcessor;

        private ServerStreamManager mParent;
        private ServerCallStreamObserver<ResMessage> mObserver;

        /*public*/ void setUp(ReqProcessor mProcessor, Scheduler mScheduler, ExceptionProcessor mExceptionProcessor) {
            this.mProcessor = mProcessor;
            this.mScheduler = mScheduler;
            this.mExceptionProcessor = mExceptionProcessor;
        }

        public void end(){
            if(mCanceled.compareAndSet(false, true)){
                synchronized (mParent.mUsers){
                    mParent.mUsers.remove(this);
                }
            }
        }
        public void addMessage(ResMessage msg){
            mScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    mObserver.onNext(msg);
                }
            });
        }
        /**
         * send message right now. this must called in sub thread.
         * @param msg the message
         */
        public void sendMessage(ResMessage msg){
            mObserver.onNext(msg);
        }
        public boolean isEnd(){
            return mCanceled.get();
        }
        public void reset(){
            mCanceled.set(false);
        }
        public ServerStreamManager getServerStreamManager(){
            return mParent;
        }
    }

    private static class IOStreamingImpl extends IOStreamingGrpc.IOStreamingImplBase {

        private final ServerStreamManager manager;

        private IOStreamingImpl(ServerStreamManager manager) {
            this.manager = manager;
        }
        @Override
        public StreamObserver<ReqMessage> reqStreaming(StreamObserver<ResMessage> responseObserver) {
            sLogger.info("reqStreaming");
            final UserSpace userSpace = manager.newUserSpace();

            ServerCallStreamObserver<ResMessage> resObserver = (ServerCallStreamObserver<ResMessage>) responseObserver;
            OnReadyHandler mReadHandler = new OnReadyHandler(userSpace, resObserver);
            resObserver.disableAutoRequest();
            resObserver.setOnReadyHandler(mReadHandler);
            userSpace.mObserver = resObserver;
//            resObserver.setOnCancelHandler(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("server --- received cancel.");
//                }
//            });
//            resObserver.setOnCloseHandler(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("server --- received close.");
//                }
//            });
            return new ReqObserver(userSpace, resObserver, mReadHandler);
        }
    };
    private static class OnReadyHandler implements Runnable {

        final UserSpace userSpace;
        ServerCallStreamObserver<ResMessage> mObserver;
        boolean wasReady = false;

        public OnReadyHandler(UserSpace userSpace, ServerCallStreamObserver<ResMessage> mObserver) {
            this.userSpace = userSpace;
            this.mObserver = mObserver;
        }
        @Override
        public void run() {
            if (mObserver.isReady() && !wasReady) {
                wasReady = true;
                sLogger.info("READY");

                mObserver.request(1);
            }
        }
    }

    private static class ReqObserver implements StreamObserver<ReqMessage>{

        final UserSpace userSpace;
        final ServerCallStreamObserver<ResMessage> mObserver;
        final OnReadyHandler mReadyHandler;

        public ReqObserver(UserSpace userSpace, ServerCallStreamObserver<ResMessage> mObserver, OnReadyHandler mReadyHandler) {
            this.userSpace = userSpace;
            this.mObserver = mObserver;
            this.mReadyHandler = mReadyHandler;
        }
        @Override
        public void onNext(ReqMessage req) {
            sLogger.info("server >>> onNext");
            try {
                ResMessage res = userSpace.mProcessor.process(userSpace, req);
                mObserver.onNext(res);
                if (mObserver.isReady()) {
                    mObserver.request(1);
                } else {
                    mReadyHandler.wasReady = false;
                }
            }catch (Throwable e){
                if(userSpace.mExceptionProcessor != null){
                    userSpace.mExceptionProcessor.process(userSpace, e);
                }else{
                    e.printStackTrace();
                }
                mObserver.onError(
                        Status.UNKNOWN.withDescription("Error handling request").withCause(e).asException());
            }
        }
        @Override
        public void onError(Throwable e) {
            sLogger.info("server >>> onError");
            mObserver.onCompleted();
            if(userSpace.mExceptionProcessor != null){
                userSpace.mExceptionProcessor.process(userSpace, e);
            }else{
                e.printStackTrace();
            }
        }
        @Override
        public void onCompleted() {
            sLogger.info("server >>> onCompleted");
            mObserver.onCompleted();
            userSpace.end();
        }
    }

    public interface ReqProcessor{
        ResMessage process(UserSpace userSpace, ReqMessage req);
    }
    public interface ExceptionProcessor{
        void process(UserSpace userSpace, Throwable e);
    }
}
