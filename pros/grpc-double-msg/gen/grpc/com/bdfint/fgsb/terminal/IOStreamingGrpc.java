package com.bdfint.fgsb.terminal;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: io_streaming.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class IOStreamingGrpc {

  private IOStreamingGrpc() {}

  public static final String SERVICE_NAME = "com.bdfint.fgsb.terminal.IOStreaming";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.bdfint.fgsb.terminal.ReqMessage,
      com.bdfint.fgsb.terminal.ResMessage> getReqStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reqStreaming",
      requestType = com.bdfint.fgsb.terminal.ReqMessage.class,
      responseType = com.bdfint.fgsb.terminal.ResMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.bdfint.fgsb.terminal.ReqMessage,
      com.bdfint.fgsb.terminal.ResMessage> getReqStreamingMethod() {
    io.grpc.MethodDescriptor<com.bdfint.fgsb.terminal.ReqMessage, com.bdfint.fgsb.terminal.ResMessage> getReqStreamingMethod;
    if ((getReqStreamingMethod = IOStreamingGrpc.getReqStreamingMethod) == null) {
      synchronized (IOStreamingGrpc.class) {
        if ((getReqStreamingMethod = IOStreamingGrpc.getReqStreamingMethod) == null) {
          IOStreamingGrpc.getReqStreamingMethod = getReqStreamingMethod =
              io.grpc.MethodDescriptor.<com.bdfint.fgsb.terminal.ReqMessage, com.bdfint.fgsb.terminal.ResMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "reqStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bdfint.fgsb.terminal.ReqMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bdfint.fgsb.terminal.ResMessage.getDefaultInstance()))
              .setSchemaDescriptor(new IOStreamingMethodDescriptorSupplier("reqStreaming"))
              .build();
        }
      }
    }
    return getReqStreamingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IOStreamingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IOStreamingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IOStreamingStub>() {
        @java.lang.Override
        public IOStreamingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IOStreamingStub(channel, callOptions);
        }
      };
    return IOStreamingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IOStreamingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IOStreamingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IOStreamingBlockingStub>() {
        @java.lang.Override
        public IOStreamingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IOStreamingBlockingStub(channel, callOptions);
        }
      };
    return IOStreamingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IOStreamingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IOStreamingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IOStreamingFutureStub>() {
        @java.lang.Override
        public IOStreamingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IOStreamingFutureStub(channel, callOptions);
        }
      };
    return IOStreamingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class IOStreamingImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.bdfint.fgsb.terminal.ReqMessage> reqStreaming(
        io.grpc.stub.StreamObserver<com.bdfint.fgsb.terminal.ResMessage> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReqStreamingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReqStreamingMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.bdfint.fgsb.terminal.ReqMessage,
                com.bdfint.fgsb.terminal.ResMessage>(
                  this, METHODID_REQ_STREAMING)))
          .build();
    }
  }

  /**
   */
  public static final class IOStreamingStub extends io.grpc.stub.AbstractAsyncStub<IOStreamingStub> {
    private IOStreamingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IOStreamingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IOStreamingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.bdfint.fgsb.terminal.ReqMessage> reqStreaming(
        io.grpc.stub.StreamObserver<com.bdfint.fgsb.terminal.ResMessage> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getReqStreamingMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class IOStreamingBlockingStub extends io.grpc.stub.AbstractBlockingStub<IOStreamingBlockingStub> {
    private IOStreamingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IOStreamingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IOStreamingBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class IOStreamingFutureStub extends io.grpc.stub.AbstractFutureStub<IOStreamingFutureStub> {
    private IOStreamingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IOStreamingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IOStreamingFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REQ_STREAMING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final IOStreamingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(IOStreamingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQ_STREAMING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.reqStreaming(
              (io.grpc.stub.StreamObserver<com.bdfint.fgsb.terminal.ResMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class IOStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IOStreamingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.bdfint.fgsb.terminal.IOMsgStreamingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IOStreaming");
    }
  }

  private static final class IOStreamingFileDescriptorSupplier
      extends IOStreamingBaseDescriptorSupplier {
    IOStreamingFileDescriptorSupplier() {}
  }

  private static final class IOStreamingMethodDescriptorSupplier
      extends IOStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    IOStreamingMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IOStreamingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IOStreamingFileDescriptorSupplier())
              .addMethod(getReqStreamingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
