package io.imulab.q.flow;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.1)",
    comments = "Source: CodeHybridFlow.proto")
public final class CodeHybridFlowGrpc {

  private CodeHybridFlowGrpc() {}

  public static final String SERVICE_NAME = "flow.CodeHybridFlow";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.imulab.q.flow.AuthorizeRequest,
      io.imulab.q.flow.AuthorizeResponse> getAuthorizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Authorize",
      requestType = io.imulab.q.flow.AuthorizeRequest.class,
      responseType = io.imulab.q.flow.AuthorizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.imulab.q.flow.AuthorizeRequest,
      io.imulab.q.flow.AuthorizeResponse> getAuthorizeMethod() {
    io.grpc.MethodDescriptor<io.imulab.q.flow.AuthorizeRequest, io.imulab.q.flow.AuthorizeResponse> getAuthorizeMethod;
    if ((getAuthorizeMethod = CodeHybridFlowGrpc.getAuthorizeMethod) == null) {
      synchronized (CodeHybridFlowGrpc.class) {
        if ((getAuthorizeMethod = CodeHybridFlowGrpc.getAuthorizeMethod) == null) {
          CodeHybridFlowGrpc.getAuthorizeMethod = getAuthorizeMethod = 
              io.grpc.MethodDescriptor.<io.imulab.q.flow.AuthorizeRequest, io.imulab.q.flow.AuthorizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "flow.CodeHybridFlow", "Authorize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.imulab.q.flow.AuthorizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.imulab.q.flow.AuthorizeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CodeHybridFlowMethodDescriptorSupplier("Authorize"))
                  .build();
          }
        }
     }
     return getAuthorizeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.imulab.q.flow.TokenRequest,
      io.imulab.q.flow.TokenResponse> getTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Token",
      requestType = io.imulab.q.flow.TokenRequest.class,
      responseType = io.imulab.q.flow.TokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.imulab.q.flow.TokenRequest,
      io.imulab.q.flow.TokenResponse> getTokenMethod() {
    io.grpc.MethodDescriptor<io.imulab.q.flow.TokenRequest, io.imulab.q.flow.TokenResponse> getTokenMethod;
    if ((getTokenMethod = CodeHybridFlowGrpc.getTokenMethod) == null) {
      synchronized (CodeHybridFlowGrpc.class) {
        if ((getTokenMethod = CodeHybridFlowGrpc.getTokenMethod) == null) {
          CodeHybridFlowGrpc.getTokenMethod = getTokenMethod = 
              io.grpc.MethodDescriptor.<io.imulab.q.flow.TokenRequest, io.imulab.q.flow.TokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "flow.CodeHybridFlow", "Token"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.imulab.q.flow.TokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.imulab.q.flow.TokenResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CodeHybridFlowMethodDescriptorSupplier("Token"))
                  .build();
          }
        }
     }
     return getTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CodeHybridFlowStub newStub(io.grpc.Channel channel) {
    return new CodeHybridFlowStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CodeHybridFlowBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CodeHybridFlowBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CodeHybridFlowFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CodeHybridFlowFutureStub(channel);
  }

  /**
   */
  public static abstract class CodeHybridFlowImplBase implements io.grpc.BindableService {

    /**
     */
    public void authorize(io.imulab.q.flow.AuthorizeRequest request,
        io.grpc.stub.StreamObserver<io.imulab.q.flow.AuthorizeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuthorizeMethod(), responseObserver);
    }

    /**
     */
    public void token(io.imulab.q.flow.TokenRequest request,
        io.grpc.stub.StreamObserver<io.imulab.q.flow.TokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTokenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuthorizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.imulab.q.flow.AuthorizeRequest,
                io.imulab.q.flow.AuthorizeResponse>(
                  this, METHODID_AUTHORIZE)))
          .addMethod(
            getTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.imulab.q.flow.TokenRequest,
                io.imulab.q.flow.TokenResponse>(
                  this, METHODID_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class CodeHybridFlowStub extends io.grpc.stub.AbstractStub<CodeHybridFlowStub> {
    private CodeHybridFlowStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CodeHybridFlowStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CodeHybridFlowStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CodeHybridFlowStub(channel, callOptions);
    }

    /**
     */
    public void authorize(io.imulab.q.flow.AuthorizeRequest request,
        io.grpc.stub.StreamObserver<io.imulab.q.flow.AuthorizeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuthorizeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void token(io.imulab.q.flow.TokenRequest request,
        io.grpc.stub.StreamObserver<io.imulab.q.flow.TokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CodeHybridFlowBlockingStub extends io.grpc.stub.AbstractStub<CodeHybridFlowBlockingStub> {
    private CodeHybridFlowBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CodeHybridFlowBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CodeHybridFlowBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CodeHybridFlowBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.imulab.q.flow.AuthorizeResponse authorize(io.imulab.q.flow.AuthorizeRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuthorizeMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.imulab.q.flow.TokenResponse token(io.imulab.q.flow.TokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CodeHybridFlowFutureStub extends io.grpc.stub.AbstractStub<CodeHybridFlowFutureStub> {
    private CodeHybridFlowFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CodeHybridFlowFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CodeHybridFlowFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CodeHybridFlowFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.imulab.q.flow.AuthorizeResponse> authorize(
        io.imulab.q.flow.AuthorizeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuthorizeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.imulab.q.flow.TokenResponse> token(
        io.imulab.q.flow.TokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTHORIZE = 0;
  private static final int METHODID_TOKEN = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CodeHybridFlowImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CodeHybridFlowImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHORIZE:
          serviceImpl.authorize((io.imulab.q.flow.AuthorizeRequest) request,
              (io.grpc.stub.StreamObserver<io.imulab.q.flow.AuthorizeResponse>) responseObserver);
          break;
        case METHODID_TOKEN:
          serviceImpl.token((io.imulab.q.flow.TokenRequest) request,
              (io.grpc.stub.StreamObserver<io.imulab.q.flow.TokenResponse>) responseObserver);
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

  private static abstract class CodeHybridFlowBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CodeHybridFlowBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.imulab.q.flow.CodeHybridFlowProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CodeHybridFlow");
    }
  }

  private static final class CodeHybridFlowFileDescriptorSupplier
      extends CodeHybridFlowBaseDescriptorSupplier {
    CodeHybridFlowFileDescriptorSupplier() {}
  }

  private static final class CodeHybridFlowMethodDescriptorSupplier
      extends CodeHybridFlowBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CodeHybridFlowMethodDescriptorSupplier(String methodName) {
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
      synchronized (CodeHybridFlowGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CodeHybridFlowFileDescriptorSupplier())
              .addMethod(getAuthorizeMethod())
              .addMethod(getTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
