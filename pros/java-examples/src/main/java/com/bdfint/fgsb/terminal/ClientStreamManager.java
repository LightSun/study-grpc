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
            if(mStreamObserver != null){
                mStreamObserver.onCompleted();
                return true;
            }
        }
        return false;
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
    public void reset(){
        mCanceled.set(false);
    }

    private final AtomicBoolean mCanceled = new AtomicBoolean(false);
    private ResProcessor mResProcessor;
    private ClientCallStreamObserver<ReqMessage> mStreamObserver;
    private final LinkedList<ReqMessage> mStack = new LinkedList<>();

    private class ClientResponseObserveImpl implements ClientResponseObserver<ReqMessage, ResMessage> {
        final CountDownLatch countDownLatch;

        public ClientResponseObserveImpl(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void beforeStart(ClientCallStreamObserver<ReqMessage> requestStream) {
            System.out.println(" client >>> beforeStart --- ");
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
            t.printStackTrace();
            countDownLatch.countDown();
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
}
