package com.bdfint.fgsb.terminal;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ClientStreamManager {

    public static final String TAG = "ClientStreamManager";

    public boolean end(){
        if(mCanceled.compareAndSet(false, true)){
            synchronized (this){
                this.notify();
            }
            if(mStreamObserver != null){
                mStreamObserver.onCompleted();
            }
        }
        synchronized (mStack){
            mStack.clear();
        }
        mStarted.set(false);
        return false;
    }

    public void startAsync(String addr, int port){
        if(!mStarted.compareAndSet(false, true)){
            System.err.println("ClientStreamManager: already started.");
            return;
        }
        mCanceled.set(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    start(addr, port);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //addr like: localhost
    public void start(String addr, int port) throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(1);
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(addr, port)
                .usePlaintext()
                .build();
        IOStreamingGrpc.IOStreamingStub stub = IOStreamingGrpc.newStub(channel);
        //IOStreamingGrpc.IOStreamingBlockingStub stub = IOStreamingGrpc.newBlockingStub(channel);
        stub.reqStreaming(new ClientResponseObserveImpl(done));
        done.await();

        channel.shutdown();
        channel.awaitTermination(1, TimeUnit.SECONDS);
    }


    public void setResProcessor(ResProcessor mResProcessor) {
        this.mResProcessor = mResProcessor;
    }

    public void addMessage(ReqMessage msg){
        synchronized (mStack){
            mStack.addLast(msg);
        }
        synchronized (this){
            this.notify();
        }
    }
    public boolean isStarted(){
        return mStarted.get();
    }
    public void reset(){
        mCanceled.set(false);
    }
    public void setOnErrorListener(OnErrorListener l){
        this.mOnErrorL = l;
    }
    //---------------------------
    private final AtomicBoolean mCanceled = new AtomicBoolean(false);
    private final AtomicBoolean mStarted = new AtomicBoolean(false);

    private ResProcessor mResProcessor;
    private ClientCallStreamObserver<ReqMessage> mStreamObserver;
    private final LinkedList<ReqMessage> mStack = new LinkedList<>();
    private OnErrorListener mOnErrorL;


    private class ClientResponseObserveImpl implements ClientResponseObserver<ReqMessage, ResMessage> {
        final CountDownLatch countDownLatch;

        public ClientResponseObserveImpl(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void beforeStart(ClientCallStreamObserver<ReqMessage> requestStream) {
            System.out.println(" client >>> beforeStart --- ");
            //Logger.d(TAG, "observer = " + requestStream);
            mStreamObserver = requestStream;
            requestStream.disableAutoRequestWithInitial(1);
            requestStream.setOnReadyHandler(new ReadHandler());
        }

        @Override
        public void onNext(ResMessage value) {
            System.out.println(" client >>> onNext --- ");
            mStreamObserver.request(1);
            if(mResProcessor != null){
                ReqMessage message = mResProcessor.process(value);
                if(message != null){
                    addMessage(message);
                }
            }
        }

        @Override
        public void onError(Throwable t) {
            System.err.println(TAG + " >> onError");
            t.printStackTrace();
            end();
            countDownLatch.countDown();
            if(mOnErrorL != null){
                mOnErrorL.onError(t);
            }
        }
        @Override
        public void onCompleted() {
            System.out.println(TAG + " >> all done");
            countDownLatch.countDown();
        }
    };
    private class ReadHandler implements Runnable{
        @Override
        public void run() {
            System.out.println(TAG + " >>> Ready --- ");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (mStreamObserver.isReady() && !mCanceled.get()) {
                            ReqMessage req;
                            synchronized (mStack){
                                req = mStack.pollFirst();
                            }
                            if(req != null){
                                mStreamObserver.onNext(req);
                                System.out.println("--- send message ok.");
                            }else {
                                System.out.println(TAG + " >>> no msg. wait for new msg.");
                                synchronized (ClientStreamManager.this){
                                    ClientStreamManager.this.wait();
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public interface ResProcessor{
        ReqMessage process(ResMessage msg);
    }
    public interface OnErrorListener{
        void onError(Throwable e);
    }
}
