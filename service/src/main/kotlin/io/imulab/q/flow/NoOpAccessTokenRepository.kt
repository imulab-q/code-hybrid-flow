package io.imulab.q.flow

import io.imulab.connect.AccessTokenRepository
import io.imulab.connect.Session

/**
 * A do nothing implementation of [AccessTokenRepository], as we are using stateless access token.
 */
class NoOpAccessTokenRepository : AccessTokenRepository {

    override suspend fun delete(token: String) {}

    override suspend fun deleteByRequestId(requestId: String) {}

    override suspend fun getSession(token: String): Session =
        throw NotImplementedError("this method should not be called.")

    override suspend fun save(token: String, session: Session) {}
}