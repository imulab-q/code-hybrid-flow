package io.imulab.q.flow

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import io.grpc.health.v1.HealthCheckRequest
import io.grpc.health.v1.HealthCheckResponse
import io.grpc.health.v1.HealthGrpc
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import redis.clients.jedis.Jedis
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class HealthCheck(
    private val jedis: Jedis,
    private val rabbit: ConnectionFactory
) : HealthGrpc.HealthImplBase(), CoroutineScope {

    private val logger = LoggerFactory.getLogger(HealthCheck::class.java)

    override val coroutineContext: CoroutineContext
        get() = Executors.newFixedThreadPool(1).asCoroutineDispatcher()

    override fun check(request: HealthCheckRequest?, responseObserver: StreamObserver<HealthCheckResponse>?) {
        launch {
            try {
                val redisHealth = launch(Dispatchers.IO) {
                    jedis.ping()
                }

                val rabbitHealth = launch(Dispatchers.IO) {
                    var connection: Connection? = null
                    var channel: Channel? = null
                    try {
                        connection = rabbit.newConnection()
                        channel = connection.createChannel()
                        channel.queueDeclare("code_hybrid_flow_service_health", false, true, true, emptyMap())
                    } finally {
                        channel?.close()
                        connection?.close()
                    }
                }

                redisHealth.join()
                rabbitHealth.join()

                logger.info("Health checked passed.")

                responseObserver?.onNext(
                    HealthCheckResponse.newBuilder()
                        .setStatus(HealthCheckResponse.ServingStatus.SERVING)
                        .build()
                )
            } catch (t: Throwable) {
                logger.error("Health check failed.", t)
                responseObserver?.onNext(
                    HealthCheckResponse.newBuilder()
                        .setStatus(HealthCheckResponse.ServingStatus.NOT_SERVING)
                        .build()
                )
            } finally {
                responseObserver?.onCompleted()
            }
        }
    }
}