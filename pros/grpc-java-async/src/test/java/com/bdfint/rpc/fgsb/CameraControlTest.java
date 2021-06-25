package com.bdfint.rpc.fgsb;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CameraControlTest {

    public static void main(String[] args) throws InterruptedException {
        CameraControlClient client = new CameraControlClient("127.0.0.1", 38657);
        try {
            client.login();
            client.call(SimpleReqType.Start_PTZ_VALUE);
        }finally {
            client.shutdown();
        }
    }

    private static class CameraControlClient{
        private static final Logger LOGGER = Logger.getLogger(CameraControlClient.class.getName());
        private final ManagedChannel channel;
        private final CameraControlGrpc.CameraControlBlockingStub blockingStub;
        private String token;

        public CameraControlClient(String host, int port) {
            this(ManagedChannelBuilder.forAddress(host, port)
                    .usePlaintext()
                    .build()
            );
        }
        CameraControlClient(ManagedChannel channel) {
            this.channel = channel;
            this.blockingStub = CameraControlGrpc.newBlockingStub(channel);
        }

        public void shutdown() throws InterruptedException{
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }

        public void login(){
            LOGGER.info("Will try to login "  + " ...");
            LoginInfo info = LoginInfo.newBuilder().setUsername("google/heaven7")
                    .setPassword("123456")
                    .build();
            ResInfo res;
            try {
                res = blockingStub.login(info);
                token = res.getToken();
            }catch (StatusRuntimeException e){
               throw new RuntimeException(e);
            }
            LOGGER.info("login result: code =" + res.getCode() + ", token = " + res.getToken());
        }
        public void call(int type){
            SimpleReq req = SimpleReq.newBuilder()
                    .setType(type)
                    .setToken(token)
                    .build();
            ResInfo res;
            try {
                res = blockingStub.call(req);
            }catch (StatusRuntimeException e){
                throw new RuntimeException(e);
            }
            LOGGER.info("startPTZ result: code =" + res.getCode()
                    + ", msg = " + res.getMsg()
                    + ", token = " + res.getToken());
        }
    }
}
