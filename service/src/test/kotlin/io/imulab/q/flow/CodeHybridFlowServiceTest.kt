package io.imulab.q.flow

import com.uchuhimo.konf.Config
import io.grpc.ManagedChannelBuilder
import io.grpc.Server
import io.grpc.ServerBuilder
import io.imulab.connect.*
import io.imulab.connect.client.*
import io.imulab.connect.client.Client
import io.kotlintest.Spec
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.matchers.string.shouldNotBeEmpty
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrowAny
import io.kotlintest.specs.FunSpec
import org.jose4j.jwk.JsonWebKeySet
import org.jose4j.jwk.RsaJwkGenerator
import org.jose4j.jwk.Use
import java.time.LocalDateTime

class CodeHybridFlowServiceTest : FunSpec({

    test("hybrid flow") {
        // authorize leg
        val authorizeRequest = ConnectAuthorizeRequest(
            _client = sampleClient(),
            responseTypes = mutableSetOf(ResponseType.CODE, ResponseType.ID_TOKEN),
            redirectUri = "https://client.org/callback",
            scopes = mutableSetOf("foo", OFFLINE_ACCESS, OPEN_ID),
            session = sampleSession().apply {
                grantedScopes.add(OPEN_ID)
            }
        )
        val authorizeResponse: Response = mutableMapOf()

        shouldNotThrowAny {
            handler.authorize(authorizeRequest, authorizeResponse)
        }

        val authorizeCode = authorizeResponse.getCode()
        authorizeCode.shouldNotBeEmpty()
        authorizeResponse.getIdToken().shouldNotBeEmpty()

        // token leg
        val tokenRequest = ConnectTokenRequest(
            grantTypes = mutableSetOf(GrantType.CODE),
            code = authorizeCode,
            redirectUri = "https://client.org/callback",
            _client = sampleClient()
        )
        val tokenResponse: Response = mutableMapOf()

        shouldNotThrowAny {
            handler.issueToken(tokenRequest, tokenResponse)
        }

        tokenResponse.getAccessToken().shouldNotBeEmpty()
        tokenResponse.getExpiresIn().shouldBeGreaterThan(0)
        tokenResponse.getTokenType() shouldBe "Bearer"
        tokenResponse.getRefreshToken().shouldNotBeEmpty()
        tokenResponse.getIdToken().shouldNotBeEmpty()
    }

    test("authorize code flow") {
        // authorize leg
        val authorizeRequest = ConnectAuthorizeRequest(
            _client = sampleClient(),
            responseTypes = mutableSetOf(ResponseType.CODE),
            redirectUri = "https://client.org/callback",
            scopes = mutableSetOf("foo", OFFLINE_ACCESS),
            session = sampleSession()
        )
        val authorizeResponse: Response = mutableMapOf()

        shouldNotThrowAny {
            handler.authorize(authorizeRequest, authorizeResponse)
        }

        val authorizeCode = authorizeResponse.getCode()
        authorizeCode.shouldNotBeEmpty()

        // token leg
        val tokenRequest = ConnectTokenRequest(
            grantTypes = mutableSetOf(GrantType.CODE),
            code = authorizeCode,
            redirectUri = "https://client.org/callback",
            _client = sampleClient()
        )
        val tokenResponse: Response = mutableMapOf()

        shouldNotThrowAny {
            handler.issueToken(tokenRequest, tokenResponse)
        }

        tokenResponse.getAccessToken().shouldNotBeEmpty()
        tokenResponse.getExpiresIn().shouldBeGreaterThan(0)
        tokenResponse.getTokenType() shouldBe "Bearer"
        tokenResponse.getRefreshToken().shouldNotBeEmpty()
    }

}) {
    companion object {

        lateinit var server: Server
        lateinit var handler: CodeHybridFlowHandler

        val testJwks: String = JsonWebKeySet(
            RsaJwkGenerator.generateJwk(2048).apply {
                use = Use.SIGNATURE
                keyId = "F9848756-7339-4688-8008-FB17C7575C27"
                algorithm = SigningAlgorithm.RS256.value
            }
        ).toJson()

        fun sampleClient(): Client {
            return object : NothingClient() {
                override val id: String
                    get() = "D11360DC-76BE-4932-9A19-4836627FF8A0"
                override val responseTypes: Set<ResponseType>
                    get() = setOf(ResponseType.CODE, ResponseType.TOKEN, ResponseType.ID_TOKEN)
                override val grantTypes: Set<GrantType>
                    get() = setOf(GrantType.CODE, GrantType.IMPLICIT, GrantType.REFRESH)
                override val scopes: Set<String>
                    get() = setOf("foo", "bar", OFFLINE_ACCESS, OPEN_ID)
                override val redirectUris: Set<String>
                    get() = setOf("https://client.org/callback")
                override val idTokenSignedResponseAlgorithm: SigningAlgorithm
                    get() = SigningAlgorithm.RS256
                override val idTokenEncryptedResponseAlgorithm: EncryptionAlgorithm
                    get() = EncryptionAlgorithm.NONE
                override val idTokenEncryptedResponseEncoding: EncryptionEncoding
                    get() = EncryptionEncoding.NONE
                override val secret: String
                    get() = "2ee3263f5f03456797ff36ec656e1afd"
                override val jwks: String
                    get() = testJwks
                override val jwksCache: String
                    get() = testJwks

                override fun plainTextSecret(): String {
                    return secret
                }
            }
        }

        fun sampleSession(): ConnectSession {
            return ConnectSession(
                subject = "test user",
                obfuscatedSubject = "test user",
                grantedScopes = mutableSetOf("foo", OFFLINE_ACCESS),
                authTime = LocalDateTime.now().minusMinutes(5),
                nonce = "12345678",
                finalRedirectUri = "https://client.org/callback"
            )
        }
    }

    override fun beforeSpec(spec: Spec) {
        val config = Config { addSpecs(ServiceSpec, RedisSpec, RabbitSpec, ConnectSpec) }
            .from.yaml.resource("test.yaml")
            .from.env()
            .from.systemProperties()
        server = ServerBuilder
            .forPort(config[ServiceSpec.port])
            .addService(setupService(config))
            .build()
        server.start()
        Runtime.getRuntime().addShutdownHook(Thread { server.shutdown() })

        handler = CodeHybridFlowHandler(
            CodeHybridFlowGrpc.newBlockingStub(
                ManagedChannelBuilder
                    .forAddress("localhost", config[ServiceSpec.port])
                    .usePlaintext()
                    .build()
            )
        )
    }

    override fun afterSpec(spec: Spec) {
        server.shutdown()
        server.awaitTermination()
    }
}