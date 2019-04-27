package io.imulab.q.flow

import io.grpc.StatusRuntimeException
import io.imulab.connect.AuthorizeRequest
import io.imulab.connect.Response
import io.imulab.connect.TokenRequest
import io.imulab.connect.client.GrantType
import io.imulab.connect.client.ResponseType
import io.imulab.connect.containsExactly
import io.imulab.connect.handler.AuthorizeHandler
import io.imulab.connect.handler.TokenHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Client side implementation of [AuthorizeHandler] and [TokenHandler] to call the remove service to handle
 * authorize code flow and hybrid flow requests.
 */
class CodeHybridFlowHandler(
    private val stub: CodeHybridFlowGrpc.CodeHybridFlowBlockingStub
) : AuthorizeHandler, TokenHandler {

    override suspend fun authorize(request: AuthorizeRequest, response: Response) {
        try {
            val authorizeResponse = coroutineScope {
                async(Dispatchers.IO) {
                    stub.authorize(request.toGrpcAuthorizeRequest())
                }
            }
            response.applyAuthorizeResponse(authorizeResponse.await())
        } catch (e: StatusRuntimeException) {
            throw e.toConnectException()
        } finally {
            request.markResponseTypeAsHandled(ResponseType.CODE)
        }
    }

    override fun supports(request: AuthorizeRequest): Boolean {
        return request.responseTypes.contains(ResponseType.CODE)
    }

    override suspend fun issueToken(request: TokenRequest, response: Response) {
        try {
            val tokenResponse = coroutineScope {
                async(Dispatchers.IO) {
                    stub.token(request.toGrpcTokenRequest())
                }
            }
            response.applyTokenResponse(tokenResponse.await())
        } catch (e: StatusRuntimeException) {
            throw e.toConnectException()
        }
    }

    override fun supports(request: TokenRequest): Boolean {
        return request.grantTypes.containsExactly(GrantType.CODE)
    }

    override suspend fun updateSession(request: TokenRequest) {
        // do nothing, it will be performed on server side
    }
}