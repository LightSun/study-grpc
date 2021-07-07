package com.bdfint.base.sys.util.asuync;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.38.1)",
    comments = "Source: CameraControl.proto")
public final class CameraControlGrpc {

  private CameraControlGrpc() {}

  public static final String SERVICE_NAME = "fgsb.CameraControl";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.bdfint.base.sys.util.asuync.LoginInfo,
      com.bdfint.base.sys.util.asuync.ResInfo> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = com.bdfint.base.sys.util.asuync.LoginInfo.class,
      responseType = com.bdfint.base.sys.util.asuync.ResInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.bdfint.base.sys.util.asuync.LoginInfo,
      com.bdfint.base.sys.util.asuync.ResInfo> getLoginMethod() {
    io.grpc.MethodDescriptor<com.bdfint.base.sys.util.asuync.LoginInfo, com.bdfint.base.sys.util.asuync.ResInfo> getLoginMethod;
    if ((getLoginMethod = CameraControlGrpc.getLoginMethod) == null) {
      synchronized (CameraControlGrpc.class) {
        if ((getLoginMethod = CameraControlGrpc.getLoginMethod) == null) {
          CameraControlGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.bdfint.base.sys.util.asuync.LoginInfo, com.bdfint.base.sys.util.asuync.ResInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bdfint.base.sys.util.asuync.LoginInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bdfint.base.sys.util.asuync.ResInfo.getDefaultInstance()))
              .setSchemaDescriptor(new CameraControlMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.bdfint.base.sys.util.asuync.SimpleReq,
      com.bdfint.base.sys.util.asuync.ResInfo> getCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Call",
      requestType = com.bdfint.base.sys.util.asuync.SimpleReq.class,
      responseType = com.bdfint.base.sys.util.asuync.ResInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.bdfint.base.sys.util.asuync.SimpleReq,
      com.bdfint.base.sys.util.asuync.ResInfo> getCallMethod() {
    io.grpc.MethodDescriptor<com.bdfint.base.sys.util.asuync.SimpleReq, com.bdfint.base.sys.util.asuync.ResInfo> getCallMethod;
    if ((getCallMethod = CameraControlGrpc.getCallMethod) == null) {
      synchronized (CameraControlGrpc.class) {
        if ((getCallMethod = CameraControlGrpc.getCallMethod) == null) {
          CameraControlGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<com.bdfint.base.sys.util.asuync.SimpleReq, com.bdfint.base.sys.util.asuync.ResInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bdfint.base.sys.util.asuync.SimpleReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bdfint.base.sys.util.asuync.ResInfo.getDefaultInstance()))
              .setSchemaDescriptor(new CameraControlMethodDescriptorSupplier("Call"))
              .build();
        }
      }
    }
    return getCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CameraControlStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CameraControlStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CameraControlStub>() {
        @java.lang.Override
        public CameraControlStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CameraControlStub(channel, callOptions);
        }
      };
    return CameraControlStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CameraControlBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CameraControlBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CameraControlBlockingStub>() {
        @java.lang.Override
        public CameraControlBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CameraControlBlockingStub(channel, callOptions);
        }
      };
    return CameraControlBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CameraControlFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CameraControlFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CameraControlFutureStub>() {
        @java.lang.Override
        public CameraControlFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CameraControlFutureStub(channel, callOptions);
        }
      };
    return CameraControlFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CameraControlImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(com.bdfint.base.sys.util.asuync.LoginInfo request,
        io.grpc.stub.StreamObserver<com.bdfint.base.sys.util.asuync.ResInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void call(com.bdfint.base.sys.util.asuync.SimpleReq request,
        io.grpc.stub.StreamObserver<com.bdfint.base.sys.util.asuync.ResInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.bdfint.base.sys.util.asuync.LoginInfo,
                com.bdfint.base.sys.util.asuync.ResInfo>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getCallMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.bdfint.base.sys.util.asuync.SimpleReq,
                com.bdfint.base.sys.util.asuync.ResInfo>(
                  this, METHODID_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class CameraControlStub extends io.grpc.stub.AbstractAsyncStub<CameraControlStub> {
    private CameraControlStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CameraControlStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CameraControlStub(channel, callOptions);
    }

    /**
     */
    public void login(com.bdfint.base.sys.util.asuync.LoginInfo request,
        io.grpc.stub.StreamObserver<com.bdfint.base.sys.util.asuync.ResInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void call(com.bdfint.base.sys.util.asuync.SimpleReq request,
        io.grpc.stub.StreamObserver<com.bdfint.base.sys.util.asuync.ResInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CameraControlBlockingStub extends io.grpc.stub.AbstractBlockingStub<CameraControlBlockingStub> {
    private CameraControlBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CameraControlBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CameraControlBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.bdfint.base.sys.util.asuync.ResInfo login(com.bdfint.base.sys.util.asuync.LoginInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.bdfint.base.sys.util.asuync.ResInfo call(com.bdfint.base.sys.util.asuync.SimpleReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CameraControlFutureStub extends io.grpc.stub.AbstractFutureStub<CameraControlFutureStub> {
    private CameraControlFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CameraControlFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CameraControlFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.bdfint.base.sys.util.asuync.ResInfo> login(
        com.bdfint.base.sys.util.asuync.LoginInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.bdfint.base.sys.util.asuync.ResInfo> call(
        com.bdfint.base.sys.util.asuync.SimpleReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_CALL = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CameraControlImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CameraControlImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.bdfint.base.sys.util.asuync.LoginInfo) request,
              (io.grpc.stub.StreamObserver<com.bdfint.base.sys.util.asuync.ResInfo>) responseObserver);
          break;
        case METHODID_CALL:
          serviceImpl.call((com.bdfint.base.sys.util.asuync.SimpleReq) request,
              (io.grpc.stub.StreamObserver<com.bdfint.base.sys.util.asuync.ResInfo>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CameraControlBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CameraControlBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.bdfint.base.sys.util.asuync.CameraControlProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CameraControl");
    }
  }

  private static final class CameraControlFileDescriptorSupplier
      extends CameraControlBaseDescriptorSupplier {
    CameraControlFileDescriptorSupplier() {}
  }

  private static final class CameraControlMethodDescriptorSupplier
      extends CameraControlBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CameraControlMethodDescriptorSupplier(String methodName) {
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
      synchronized (CameraControlGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CameraControlFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
