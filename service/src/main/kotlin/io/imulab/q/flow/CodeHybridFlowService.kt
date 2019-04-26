package io.imulab.q.flow

import io.grpc.stub.StreamObserver

class CodeHybridFlowService : CodeHybridFlowGrpc.CodeHybridFlowImplBase() {

    override fun authorize(request: AuthorizeRequest?, responseObserver: StreamObserver<AuthorizeResponse>?) {
        super.authorize(request, responseObserver)
    }

    override fun token(request: TokenRequest?, responseObserver: StreamObserver<TokenResponse>?) {
        super.token(request, responseObserver)
    }
}