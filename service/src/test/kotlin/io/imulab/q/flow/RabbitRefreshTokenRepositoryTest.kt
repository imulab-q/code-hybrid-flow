package io.imulab.q.flow

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.uchuhimo.konf.Config
import io.imulab.connect.ConnectSession
import io.kotlintest.Spec
import io.kotlintest.matchers.string.shouldNotBeEmpty
import io.kotlintest.shouldNotThrowAny
import io.kotlintest.specs.FunSpec
import java.nio.charset.StandardCharsets
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RabbitRefreshTokenRepositoryTest : FunSpec({

    test("session should be sent to queue successfully") {
        val token = "some.token"
        val session = sampleSession()
        val latch = CountDownLatch(1)

        channel.queueDeclare(queueName, true, false, false, null)
        channel.basicConsume(queueName, true, DeliverCallback { _, message ->
            try {
                message.body.toString(StandardCharsets.UTF_8).shouldNotBeEmpty()
            } finally {
                latch.countDown()
            }
        }, CancelCallback {})

        shouldNotThrowAny {
            repository.save(token, session)
        }

        shouldNotThrowAny {
            if (!latch.await(5, TimeUnit.SECONDS))
                throw RuntimeException("did not receive message in time")
        }
    }

}) {
    companion object {
        const val queueName = "refresh_token"
        lateinit var channel: Channel
        lateinit var repository: RabbitRefreshTokenRepository

        val sampleSession = {
            ConnectSession(
                subject = "foo",
                grantedScopes = mutableSetOf("foo", "bar"),
                idClaims = mutableMapOf(
                    "foo" to "bar",
                    "foo2" to listOf("a", "b"),
                    "foo3" to mapOf("one" to "1")
                ),
                acrValues = mutableListOf("one", "two")
            )
        }
    }

    override fun beforeSpec(spec: Spec) {
        val config = Config { addSpec(RabbitSpec) }.from.env()

        channel = ConnectionFactory().apply {
            host = config[RabbitSpec.host]
            port = config[RabbitSpec.port]
        }.newConnection().createChannel()

        repository = RabbitRefreshTokenRepository(
            channel = channel,
            queueName = queueName
        )
    }
}