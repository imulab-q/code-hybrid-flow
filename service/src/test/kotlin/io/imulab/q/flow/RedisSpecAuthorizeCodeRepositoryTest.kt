package io.imulab.q.flow

import com.uchuhimo.konf.Config
import io.imulab.connect.ConnectException
import io.imulab.connect.ConnectSession
import io.imulab.connect.Session
import io.kotlintest.*
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.maps.shouldContainExactly
import io.kotlintest.specs.FunSpec
import redis.clients.jedis.Jedis
import java.time.Duration

class RedisSpecAuthorizeCodeRepositoryTest : FunSpec({

    test("authorize code session should be saved and revived properly") {
        val code = "sample.code"
        val session = sampleSession()

        shouldNotThrowAny {
            repository.save(code, session)
        }

        val recovered = repository.getSession(code)
        recovered should beSample
    }

    test("authorize code session should be deleted properly") {
        val code = "sample.code"
        val session = sampleSession()

        shouldNotThrowAny {
            repository.save(code, session)
        }

        shouldNotThrowAny {
            repository.delete(code)
        }

        shouldThrow<ConnectException> {
            repository.getSession(code)
        }
    }
}) {

    companion object {
        lateinit var repository: RedisAuthorizeCodeRepository

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

        val beSample: (Session) -> Unit = {
            val truth = sampleSession()
            it.subject shouldBe truth.subject
            it.grantedScopes shouldContainExactly truth.grantedScopes
            it.acrValues shouldContainExactly truth.acrValues
            it.idClaims shouldContainExactly truth.idClaims
        }
    }

    override fun beforeSpec(spec: Spec) {
        val config = Config { addSpec(RedisSpec) }
            .from.yaml.resource("test.yaml")
            .from.env()

        repository = RedisAuthorizeCodeRepository(
            jedis = Jedis(config[RedisSpec.host], config[RedisSpec.port]),
            lifespan = Duration.ofMinutes(10)
        )
    }
}