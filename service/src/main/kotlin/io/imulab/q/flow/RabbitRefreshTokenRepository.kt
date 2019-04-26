package io.imulab.q.flow

import com.google.gson.Gson
import com.rabbitmq.client.Channel
import io.imulab.connect.Errors
import io.imulab.connect.RefreshTokenRepository
import io.imulab.connect.Session
import org.slf4j.LoggerFactory

/**
 * A partial [RefreshTokenRepository] implementation that sends refresh token session off to a message queue
 * to be consumed by other services.
 *
 * This implementation is partial because it only concerns with the saving part. Since the refresh token is not
 * exchanged at this service, [getSession], [delete] and [deleteByRequestId] is never called and hence not
 * implemented.
 */
class RabbitRefreshTokenRepository(
    private val channel: Channel,
    private val queueName: String = "refresh_token",
    private val gson: Gson = Gson()
) : RefreshTokenRepository {

    private val logger = LoggerFactory.getLogger(RabbitRefreshTokenRepository::class.java)

    override suspend fun delete(token: String) {}

    override suspend fun deleteByRequestId(requestId: String) {}

    override suspend fun getSession(token: String): Session = throw NotImplementedError("this should not be called.")

    override suspend fun save(token: String, session: Session) {
        try {
            channel.queueDeclare(queueName, true, false, false, null)
            channel.basicPublish("", queueName, null, gson.toJson(Payload(token, session)).toByteArray())
            logger.info("session for refresh token $token is published.")
        } catch (t: Throwable) {
            logger.error("unable to publish refresh token session.", t)
            throw Errors.serverError("unable to create refresh token session.")
        }
    }

    internal class Payload(val token: String, val session: Session)
}