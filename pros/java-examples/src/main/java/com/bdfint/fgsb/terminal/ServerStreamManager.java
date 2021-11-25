package com.bdfint.fgsb.terminal;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public final class ServerStreamManager {

    private static final String TAG = "ServerStreamManager";
    private static final Logger logger =
            Logger.getLogger(ServerStreamManager.class.getName());

    private final ReqProcessor mProcessor;
    private final LinkedList<ResMessage> mStack = new LinkedList<>();
    private final Scheduler mScheduler;

    private Server mServer;
    private ExceptionProcessor mExceptionProcessor;
    private final AtomicBoolean mCanceled = new AtomicBoolean(false);

    public ServerStreamManager(Scheduler scheduler, ReqProcessor mProcessor) {
        this.mScheduler = scheduler;
        this.mProcessor = mProcessor;
    }
    /**
     * start server with target the port
     * @param port the port to listen
     * @throws IOException if already started or shut down
     * IOException if unable to bind
     * @throws InterruptedException if trigger interrupt
     */
    public void start(int port) throws IOException, InterruptedException {
        final Server server = ServerBuilder
                .forPort(port)
                .addService(mSvc)
                .build()
                .start();
        mServer = server;

        logger.info("Listening on " + server.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("Shutting down");
                end();
            }
        });
        server.awaitTermination();
        logger.info("Server end. awaitTermination ");
    }

    public void addMessage(ResMessage msg){
        synchronized (mStack){
            mStack.addLast(msg);
        }
        synchronized (this){
            notify();
        }
    }

    public void end(){
        if(mCanceled.compareAndSet(false, true)){
            synchronized (this){
                notify();
            }
            if(mServer != null){
                try {
                    mServer.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                    mServer = null;
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public boolean isEnd(){
        return mCanceled.get();
    }
    public void reset(){
        mCanceled.set(false);
    }
    public void setExceptionProcessor(ExceptionProcessor mExceptionProcessor) {
        this.mExceptionProcessor = mExceptionProcessor;
    }

    private final IOStreamingGrpc.IOStreamingImplBase mSvc = new IOStreamingGrpc.IOStreamingImplBase() {
        @Override
        public StreamObserver<ReqMessage> reqStreaming(StreamObserver<ResMessage> responseObserver) {
            logger.info("reqStreaming");
            ServerCallStreamObserver<ResMessage> resObserver = (ServerCallStreamObserver<ResMessage>) responseObserver;
            OnReadyHandler mReadHandler = new OnReadyHandler(resObserver);
            resObserver.disableAutoRequest();
            resObserver.setOnReadyHandler(mReadHandler);
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
            return new ReqObserver(resObserver, mReadHandler);
        }
    };
    private class OnReadyHandler implements Runnable {

        ServerCallStreamObserver<ResMessage> mObserver;
        boolean wasReady = false;

        public OnReadyHandler(ServerCallStreamObserver<ResMessage> mObserver) {
            this.mObserver = mObserver;
        }
        @Override
        public void run() {
            if (mObserver.isReady() && !wasReady) {
                wasReady = true;
                logger.info("READY");

                mObserver.request(1);
            }
            if(mScheduler == null){
                logger.info("no scheduler: push message to client is disabled.");
                return;
            }
            mScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!mCanceled.get()){
                            ResMessage req;
                            synchronized (mStack){
                                req = mStack.pollFirst();
                            }
                            if(req != null){
                                mObserver.onNext(req);
                            }else {
                                System.out.println(TAG + " >>> no msg. wait for new msg.");
                                synchronized (ServerStreamManager.this){
                                    ServerStreamManager.this.wait();
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private class ReqObserver implements StreamObserver<ReqMessage>{

        final ServerCallStreamObserver<ResMessage> mObserver;
        final OnReadyHandler mReadyHandler;

        public ReqObserver(ServerCallStreamObserver<ResMessage> mObserver, OnReadyHandler mReadHandler) {
            this.mObserver = mObserver;
            this.mReadyHandler = mReadHandler;
        }
        @Override
        public void onNext(ReqMessage req) {
            logger.info("server >>> onNext");
            try {
                ResMessage res = mProcessor.process(ServerStreamManager.this, req);
                mObserver.onNext(res);
                if (mObserver.isReady()) {
                    mObserver.request(1);
                } else {
                    mReadyHandler.wasReady = false;
                }
            }catch (Throwable e){
                if(mExceptionProcessor != null){
                    mExceptionProcessor.process(e);
                }else{
                    e.printStackTrace();
                }
                mObserver.onError(
                        Status.UNKNOWN.withDescription("Error handling request").withCause(e).asException());
            }
        }
        @Override
        public void onError(Throwable e) {
            logger.info("server >>> onError");
            if(mExceptionProcessor != null){
                mExceptionProcessor.process(e);
            }else{
                e.printStackTrace();
            }
            mObserver.onCompleted();
        }
        @Override
        public void onCompleted() {
            logger.info("server >>> onCompleted");
            mObserver.onCompleted();
        }
    }

    public interface ReqProcessor{
        ResMessage process(ServerStreamManager ctx, ReqMessage req);
    }
    public interface ExceptionProcessor{
        void process(Throwable e);
    }
}
