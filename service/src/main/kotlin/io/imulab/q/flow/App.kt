package io.imulab.q.flow

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.rabbitmq.client.ConnectionFactory
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec
import com.uchuhimo.konf.Spec
import io.grpc.ServerBuilder
import io.imulab.connect.*
import io.imulab.connect.client.SigningAlgorithm
import io.imulab.connect.handler.AuthorizeCodeFlowHandler
import io.imulab.connect.handler.HybridFlowHandler
import org.jose4j.jwk.JsonWebKeySet
import org.jose4j.keys.AesKey
import redis.clients.jedis.Jedis
import java.io.File
import java.nio.charset.StandardCharsets
import java.time.Duration

fun main() {
    val config = parseConfig()
    val service = setupService(config)
    val server = ServerBuilder
        .forPort(config[ServiceSpec.port])
        .addService(service)
        .build()

    server.start()
    Runtime.getRuntime().addShutdownHook(Thread { server?.shutdown() })
    server?.awaitTermination()
}

/**
 * Read config in the following order: application.yaml -> environment variable -> system properties, with
 * the latter overriding the former.
 */
internal fun parseConfig(): Config =
    Config { addSpecs(ServiceSpec, RedisSpec, RabbitSpec, ConnectSpec) }
        .from.yaml.resource("application.yaml")
        .from.env()
        .from.systemProperties()

/**
 * Logic to wire up all components for [CodeHybridFlowService].
 */
internal fun setupService(config: Config): CodeHybridFlowService {
    val discovery = when {
        config[ConnectSpec.DiscoverySpec.value].isNotEmpty() ->
            Gson().fromJson(config[ConnectSpec.DiscoverySpec.value], Discovery::class.java)
        config[ConnectSpec.DiscoverySpec.path].isNotEmpty() ->
            Gson().fromJson(File(config[ConnectSpec.DiscoverySpec.path]).readText(), Discovery::class.java)
        else -> throw IllegalStateException("one of connect.discovery.[value|path] option must be specified.")
    }

    val jwks = when {
        config[ConnectSpec.JwksSpec.value].isNotEmpty() ->
            JsonWebKeySet(config[ConnectSpec.JwksSpec.value])
        config[ConnectSpec.JwksSpec.path].isNotEmpty() ->
            JsonWebKeySet(File(config[ConnectSpec.JwksSpec.path]).readText())
        else -> throw IllegalStateException("one of connect.jwks.[value|path] option must be specified.")
    }

    val authorizeCodeHelper = AuthorizeCodeHelper(
        strategy = HmacAuthorizeCodeStrategy(
            signingAlgorithm = SigningAlgorithm.HS256,
            entropy = config[ConnectSpec.CodeSpec.entropy],
            key = AesKey(config[ConnectSpec.CodeSpec.secret].toByteArray(StandardCharsets.UTF_8))
        ),
        repository = RedisAuthorizeCodeRepository(
            jedis = Jedis(config[RedisSpec.host], config[RedisSpec.port]),
            lifespan = config[ConnectSpec.CodeSpec.lifespan],
            hashKey = config[RedisSpec.authorizeCodeHashKey]
        )
    )

    val accessTokenHelper = AccessTokenHelper(
        strategy = JwtAccessTokenStrategy(
            signingAlgorithm = SigningAlgorithm.RS256,
            lifespan = config[ConnectSpec.AccessTokenSpec.lifespan],
            issuerUrl = discovery.issuer,
            jwks = jwks
        ),
        repository = NoOpAccessTokenRepository(),
        lifespan = config[ConnectSpec.AccessTokenSpec.lifespan]
    )

    val refreshTokenHelper = RefreshTokenHelper(
        strategy = HmacRefreshTokenStrategy(
            signingAlgorithm = SigningAlgorithm.HS256,
            entropy = config[ConnectSpec.RefreshTokenSpec.entropy],
            key = AesKey(config[ConnectSpec.RefreshTokenSpec.secret].toByteArray(StandardCharsets.UTF_8))
        ),
        repository = RabbitRefreshTokenRepository(
            channel = ConnectionFactory().apply {
                host = config[RabbitSpec.host]
                port = config[RabbitSpec.port]
            }.newConnection().createChannel(),
            queueName = config[RabbitSpec.refreshTokenQueueName]
        )
    )

    val idTokenHelper = IdTokenHelper(
        strategy = JwxIdTokenStrategy(
            issuerUrl = discovery.issuer,
            lifespan = config[ConnectSpec.IdTokenSpec.lifespan],
            signingAlgorithm = SigningAlgorithm.RS256,
            jwks = jwks
        )
    )

    return CodeHybridFlowService(
        authorizeCodeFlowHandler = AuthorizeCodeFlowHandler(
            authorizeCodeHelper = authorizeCodeHelper,
            accessTokenHelper = accessTokenHelper,
            refreshTokenHelper = refreshTokenHelper,
            idTokenHelper = idTokenHelper
        ),
        hybridFlowHandler = HybridFlowHandler(
            authorizeCodeHelper = authorizeCodeHelper,
            accessTokenHelper = accessTokenHelper,
            idTokenHelper = idTokenHelper
        )
    )
}

/**
 * Configuration spec for top level "redis" key.
 */
object RedisSpec : ConfigSpec("redis") {
    val host by optional("127.0.0.1")
    val port by optional(6379)
    val authorizeCodeHashKey by optional("auth_code")

}

/**
 * Configuration spec for top level "rabbit" key.
 */
object RabbitSpec : ConfigSpec("rabbit") {
    val host by optional("127.0.0.1")
    val port by optional(5672)
    val refreshTokenQueueName by optional("refresh_token_queue")

}

/**
 * Configuration spec for top level "service" key.
 */
object ServiceSpec : ConfigSpec("service") {
    val port by optional(10080)
}

/**
 * Configuration spec for top level "connect" key.
 */
object ConnectSpec : ConfigSpec("connect") {
    object DiscoverySpec : ConfigSpec("discovery") {
        val value by optional("")
        val path by optional("")
    }

    object JwksSpec : ConfigSpec("jwks") {
        val value by optional("")
        val path by optional("")
    }

    object CodeSpec : ConfigSpec("code") {
        val lifespan by required<Duration>()
        val secret by required<String>()
        val entropy by optional(16)
    }

    object AccessTokenSpec : ConfigSpec("accessToken") {
        val lifespan by required<Duration>()
    }

    object RefreshTokenSpec : ConfigSpec("refreshToken") {
        val secret by required<String>()
        val entropy by optional(32)
    }

    object IdTokenSpec : ConfigSpec("idToken") {
        val lifespan by required<Duration>()
    }
}

/**
 * Open ID Connect 1.0 discovery data structure (only what we need)
 */
class Discovery(
    @field:SerializedName("issuer")
    val issuer: String
)

// convenience extension to add multiple config spec in one line.
// created for formatting purposes.
internal fun Config.addSpecs(vararg specs: Spec) = specs.forEach { this.addSpec(it) }
