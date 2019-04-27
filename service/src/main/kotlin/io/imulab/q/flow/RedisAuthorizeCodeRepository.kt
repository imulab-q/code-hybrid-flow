package io.imulab.q.flow

import com.google.gson.Gson
import io.imulab.connect.AuthorizeCodeRepository
import io.imulab.connect.ConnectSession
import io.imulab.connect.Errors
import io.imulab.connect.Session
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import redis.clients.jedis.Jedis
import redis.clients.jedis.params.SetParams
import java.time.Duration

/**
 * Implementation of [AuthorizeCodeRepository] that saves session in JSON form into a redis.
 *
 * The session was saved as a top level entry which all falls into the same hash bucket.
 */
class RedisAuthorizeCodeRepository(
    private val jedis: Jedis,
    private val hashKey: String = "auth_code",
    private val gson: Gson = Gson(),
    private val lifespan: Duration
) : AuthorizeCodeRepository {

    private val logger = LoggerFactory.getLogger(RedisAuthorizeCodeRepository::class.java)

    override suspend fun delete(code: String) {
        val r = coroutineScope {
            async(Dispatchers.IO) {
                jedis.del(keyOf(code))
            }
        }

        when (r.await()) {
            1L -> logger.info("session for authorize code $code is deleted.")
            else -> logger.info("session for authorize code $code is not found.")
        }
    }

    override suspend fun getSession(code: String): Session {
        val raw = coroutineScope {
            async(Dispatchers.IO) {
                jedis.get(keyOf(code)) ?: ""
            }
        }.await()

        if (raw.isEmpty()) {
            logger.error("authorize code $code is not found")
            throw Errors.invalidGrant("authorize code $code is not found")
        }

        try {
            return gson.fromJson(raw, ConnectSession::class.java)
        } catch (t: Throwable) {
            logger.error("session for authorize code $code cannot be reconstructed.", t)
            throw Errors.serverError("failed to read authorize code $code.")
        }
    }

    override suspend fun save(code: String, session: Session) {
        val r = coroutineScope {
            async(Dispatchers.IO) {
                jedis.set(
                    keyOf(code),
                    gson.toJson(session),
                    SetParams
                        .setParams()
                        .ex(lifespan.toMillis().toInt() / 1000)
                )
            }
        }

        logger.info("saving session for authorize code $code returned status ${r.await()}.")
    }

    // this guarantees that all keys fall into the same hash bucket
    private fun keyOf(code: String) = "{$hashKey}$code"
}