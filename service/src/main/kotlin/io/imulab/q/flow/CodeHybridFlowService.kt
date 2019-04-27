package io.imulab.q.flow

import io.grpc.stub.StreamObserver
import io.imulab.connect.ConnectException
import io.imulab.connect.Errors
import io.imulab.connect.Response
import io.imulab.connect.handler.AuthorizeCodeFlowHandler
import io.imulab.connect.handler.HybridFlowHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

/**
 * Authorization code flow / hybrid flow service implementation. This class serves as a bridge between the handlers
 * provided by the connect SDK and gRPC. By default, it launches as many threads as there are CPU cores to
 * execute the incoming request.
 */
class CodeHybridFlowService(
    private val authorizeCodeFlowHandler: AuthorizeCodeFlowHandler,
    private val hybridFlowHandler: HybridFlowHandler,
    private val concurrency: Int = Runtime.getRuntime().availableProcessors()
) : CodeHybridFlowGrpc.CodeHybridFlowImplBase(), CoroutineScope {

    private val logger = LoggerFactory.getLogger(CodeHybridFlowService::class.java)

    override val coroutineContext: CoroutineContext
        get() = Executors.newFixedThreadPool(concurrency).asCoroutineDispatcher()

    override fun authorize(request: AuthorizeRequest?, responseObserver: StreamObserver<AuthorizeResponse>?) {
        val authorizeRequest = request?.toConnectAuthorizeRequest() ?: return
        val authorizeResponse: Response = mutableMapOf()

        launch {
            try {
                authorizeCodeFlowHandler.authorize(authorizeRequest, authorizeResponse)
                hybridFlowHandler.authorize(authorizeRequest, authorizeResponse)

                if (!authorizeRequest.hasAllResponseTypesBeenHandled())
                    throw Errors.serverError("server was not able to handle all response types.")

                responseObserver?.onNext(authorizeResponse.toGrpcAuthorizeResponse())
                responseObserver?.onCompleted()
            } catch (t: Throwable) {
                val e = when (t) {
                    is ConnectException -> t
                    else -> Errors.serverError(t.message ?: "")
                }
                responseObserver?.onError(e.toStatusRuntimeException())
            }
        }
    }

    override fun token(request: TokenRequest?, responseObserver: StreamObserver<TokenResponse>?) {
        val tokenRequest = request?.toConnectTokenRequest() ?: return
        val tokenResponse: Response = mutableMapOf()

        launch {
            try {
                authorizeCodeFlowHandler.updateSession(tokenRequest)
                authorizeCodeFlowHandler.issueToken(tokenRequest, tokenResponse)

                responseObserver?.onNext(tokenResponse.toGrpcTokenResponse())
                responseObserver?.onCompleted()
            } catch (t: Throwable) {
                logger.error("token leg failed", t)
                val e = when (t) {
                    is ConnectException -> t
                    else -> Errors.serverError(t.message ?: "")
                }
                responseObserver?.onError(e.toStatusRuntimeException())
            }
        }
    }
}