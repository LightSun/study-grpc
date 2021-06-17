package io.grpc.examples.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TestHelloWorldClient {

    public static void main(String[] args) {
        HelloRequest req = HelloRequest.newBuilder()
                .setName("World")
                .build();

        ManagedChannel chan = ManagedChannelBuilder.forAddress("127.0.0.1", 50051).usePlaintext().build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(chan);
        HelloReply resp = stub.sayHello(req);
        System.out.println(resp.getMessage());
    }
}
