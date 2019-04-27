package io.imulab.q.flow

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.imulab.connect.*
import io.imulab.connect.AuthorizeRequest
import io.imulab.connect.TokenRequest
import io.imulab.connect.client.*
import io.imulab.connect.client.Client
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Convert connect-sdk [AuthorizeRequest] to gRPC authorize request model.
 */
internal fun AuthorizeRequest.toGrpcAuthorizeRequest(): io.imulab.q.flow.AuthorizeRequest {
    return io.imulab.q.flow.AuthorizeRequest.newBuilder().also { b ->
        b.id = this.id
        b.requestAt = this.requestedAt.toEpochSecond(ZoneOffset.UTC)
        b.addAllResponseTypes(this.responseTypes.map { it.value })
        b.redirectUri = this.redirectUri
        b.addAllScopes(this.scopes)
        b.client = this.client.toGrpcClient()
        b.session = this.session.toGrpcAuthorizeRequestSession()
    }.build()
}

/**
 * Convert gRPC authorize request model to connect-sdk [AuthorizeRequest].
 */
fun io.imulab.q.flow.AuthorizeRequest.toConnectAuthorizeRequest(): AuthorizeRequest {
    return ConnectAuthorizeRequest(
        id = this.id,
        requestedAt = LocalDateTime.ofEpochSecond(this.requestAt, 0, ZoneOffset.UTC),
        responseTypes = this.responseTypesList.map { ResponseType.parse(it) }.toMutableSet(),
        redirectUri = this.redirectUri,
        scopes = this.scopesList.toMutableSet(),
        _client = this.client.toConnectClient(),
        session = this.session.toConnectSession().also { it.clientId = this.client.id }
    )
}

/**
 * Convert connect-sdk [Session] to gRPC session model.
 */
internal fun Session.toGrpcAuthorizeRequestSession(): io.imulab.q.flow.AuthorizeRequest.Session {
    return io.imulab.q.flow.AuthorizeRequest.Session.newBuilder().also { b ->
        b.subject = if (this.obfuscatedSubject.isEmpty())
            this.subject
        else
            this.obfuscatedSubject

        b.addAllGrantedScopes(this.grantedScopes)
        b.authTime = this.authTime?.toEpochSecond(ZoneOffset.UTC) ?: 0
        b.addAllAcrValues(this.acrValues)
        b.nonce = this.nonce
        b.finalRedirectUri = this.finalRedirectUri

        Gson().apply {
            b.accessClaims = toJson(this@toGrpcAuthorizeRequestSession.accessClaims)
            b.idClaims = toJson(this@toGrpcAuthorizeRequestSession.idClaims)
        }
    }.build()
}

/**
 * Convert a gRPC session model to connect-sdk [Session].
 */
internal fun io.imulab.q.flow.AuthorizeRequest.Session.toConnectSession(): Session {
    return ConnectSession(
        subject = this.subject,
        obfuscatedSubject = this.subject,
        finalRedirectUri = this.finalRedirectUri,
        grantedScopes = this.grantedScopesList.toMutableSet(),
        accessClaims = Gson().fromJson(this.accessClaims, (object : TypeToken<HashMap<String, Any>>(){}).type),
        idClaims = Gson().fromJson(this.idClaims, (object : TypeToken<HashMap<String, Any>>(){}).type),
        authTime = if (this.authTime == 0L) null else LocalDateTime.ofEpochSecond(this.authTime, 0, ZoneOffset.UTC),
        acrValues = this.acrValuesList,
        nonce = this.nonce
    )
}

/**
 * Convert connect-sdk [TokenRequest] to gRPC token request model.
 */
internal fun TokenRequest.toGrpcTokenRequest(): io.imulab.q.flow.TokenRequest {
    return io.imulab.q.flow.TokenRequest.newBuilder().also { b ->
        b.id = this.id
        b.requestAt = this.requestedAt.toEpochSecond(ZoneOffset.UTC)
        b.addAllGrantTypes(this.grantTypes.map { it.value })
        b.code = this.code
        b.redirectUri = this.redirectUri
        b.client = this.client.toGrpcClient()
    }.build()
}

/**
 * Convert gRPC token request model to connect-sdk [TokenRequest].
 */
fun io.imulab.q.flow.TokenRequest.toConnectTokenRequest(): TokenRequest {
    return ConnectTokenRequest(
        id = this.id,
        requestedAt = LocalDateTime.ofEpochSecond(this.requestAt, 0, ZoneOffset.UTC),
        grantTypes = this.grantTypesList.map { GrantType.parse(it) }.toMutableSet(),
        code = this.code,
        redirectUri = this.redirectUri,
        _client = this.client.toConnectClient(),
        session = ConnectSession()
    )
}

/**
 * Convert connect-sdk [Response] to gRPC authorize response model.
 */
fun Response.toGrpcAuthorizeResponse(): AuthorizeResponse {
    return AuthorizeResponse.newBuilder().also { b ->
        b.code = this.getCode()
        b.accessToken = this.getAccessToken()
        b.tokenType = this.getTokenType()
        b.expiresIn = this.getExpiresIn()
        b.idToken = this.getIdToken()
    }.build()
}

/**
 * Apply data from gRPC authorize response model to connect-sdk [Response].
 */
fun Response.applyAuthorizeResponse(response: AuthorizeResponse) {
    setCode(response.code)
    setAccessToken(response.accessToken)
    setTokenType(response.tokenType)
    setExpiresIn(response.expiresIn)
    setIdToken(response.idToken)
}

/**
 * Convert connect-sdk [Response] to gRPC token response model.
 */
fun Response.toGrpcTokenResponse(): TokenResponse {
    return TokenResponse.newBuilder().also { b ->
        b.accessToken = this.getAccessToken()
        b.tokenType = this.getTokenType()
        b.expiresIn = this.getExpiresIn()
        b.refreshToken = this.getRefreshToken()
        b.idToken = this.getIdToken()
    }.build()
}

/**
 * Apply data from gRPC token response model to connect-sdk [Response].
 */
internal fun Response.applyTokenResponse(response: TokenResponse) {
    setAccessToken(response.accessToken)
    setTokenType(response.tokenType)
    setExpiresIn(response.expiresIn)
    setIdToken(response.idToken)
    setRefreshToken(response.refreshToken)
}

/**
 * Convert from connect-sdk Client to gRPC client model.
 */
internal fun Client.toGrpcClient(): io.imulab.q.flow.Client {
    return io.imulab.q.flow.Client.newBuilder().also { b ->
        b.id = this.id
        b.addAllResponseTypes(this.responseTypes.map { it.value })
        b.addAllGrantTypes(this.grantTypes.map { it.value })
        b.addAllScopes(this.scopes)
        b.addAllRedirectUris(this.redirectUris)
        b.idTokenSignAlg = this.idTokenSignedResponseAlgorithm.value
        b.idTokenEncryptAlg = this.idTokenEncryptedResponseAlgorithm.value
        b.idTokenEncryptEnc = this.idTokenEncryptedResponseEncoding.value

        if (this is JwksCacheAware)
            b.jwks = this.jwksCache
        else if (this.jwks.isNotEmpty())
            b.jwks = this.jwks

        if (this is ClientSecretAware)
            b.secret = this.plainTextSecret()
    }.build()
}

/**
 * Convert from gRPC client model to connect-sdk Client.
 */
internal fun io.imulab.q.flow.Client.toConnectClient(): Client {
    return GrpcClient(this)
}

/**
 * Internal adaptation for [Client]. Implements all and only the necessary fields for this service to function, all
 * other fields follow the logic of [NothingClient] and throws exception.
 */
private class GrpcClient(
    override val id: String,
    override val responseTypes: Set<ResponseType>,
    override val grantTypes: Set<GrantType>,
    override val scopes: Set<String>,
    override val redirectUris: Set<String>,
    override val idTokenEncryptedResponseAlgorithm: EncryptionAlgorithm,
    override val idTokenEncryptedResponseEncoding: EncryptionEncoding,
    override val idTokenSignedResponseAlgorithm: SigningAlgorithm,
    override val jwksUri: String = "",
    override val jwks: String,
    override val jwksCache: String,
    override val secret: String
) : NothingClient() {

    override fun plainTextSecret(): String = secret

    constructor(client: io.imulab.q.flow.Client) : this(
        id = client.id,
        responseTypes = client.responseTypesList.map { ResponseType.parse(it) }.toSet(),
        grantTypes = client.grantTypesList.map { GrantType.parse(it) }.toSet(),
        scopes = client.scopesList.toSet(),
        redirectUris = client.redirectUrisList.toSet(),
        idTokenSignedResponseAlgorithm = signingAlgorithmTemporaryParseFix(client.idTokenSignAlg),
        idTokenEncryptedResponseAlgorithm = encryptionAlgorithmTemporaryParseFix(client.idTokenEncryptAlg),
        idTokenEncryptedResponseEncoding = encryptionEncodingTemporaryParseFix(client.idTokenEncryptEnc),
        jwksCache = client.jwks,
        jwks = client.jwks,
        secret = client.secret
    )
}

/**
 * Convert [ConnectException] to gRPC specific [StatusRuntimeException].
 */
fun ConnectException.toStatusRuntimeException(): StatusRuntimeException {
    return StatusRuntimeException(
        Status.INTERNAL
            .withDescription(this.message ?: this.error)
            .withCause(this),
        Metadata().apply {
            put(connectErrorCodeKey, this@toStatusRuntimeException.error)
            put(connectErrorDescriptionKey, this@toStatusRuntimeException.message ?: "")
            put(connectErrorStatusCodeKey, this@toStatusRuntimeException.statusCode.toString())
            put(connectErrorHeadersKey, Gson().toJson(this@toStatusRuntimeException.headers))
        }
    )
}

/**
 * Convert gRPC specific [StatusRuntimeException] back to [ConnectException], defaulting to server_error.
 */
fun StatusRuntimeException.toConnectException(): ConnectException {
    return ConnectException(
        error = this.trailers.get(connectErrorCodeKey) ?: Errors.Codes.serverError,
        description = this.trailers.get(connectErrorDescriptionKey) ?: this.message ?: "unknown error",
        statusCode = this.trailers.get(connectErrorStatusCodeKey)?.toIntOrNull() ?: 500,
        headers = kotlin.runCatching {
            Gson().fromJson<Map<String, String>>(
                this.trailers.get(connectErrorHeadersKey) ?: "{}",
                (object : TypeToken<HashMap<String, String>>(){}).type
            )
        }.getOrDefault(emptyMap())
    )
}

internal val connectErrorCodeKey = Metadata.Key.of("connect_error_code", Metadata.ASCII_STRING_MARSHALLER)
internal val connectErrorDescriptionKey = Metadata.Key.of("connect_error_description", Metadata.ASCII_STRING_MARSHALLER)
internal val connectErrorStatusCodeKey = Metadata.Key.of("connect_status_code", Metadata.ASCII_STRING_MARSHALLER)
internal val connectErrorHeadersKey = Metadata.Key.of("connect_headers", Metadata.ASCII_STRING_MARSHALLER)

// to be removed, pending this issue:
// https://github.com/imulab/connect-sdk/issues/4
private fun signingAlgorithmTemporaryParseFix(value: String): SigningAlgorithm {
    return SigningAlgorithm.values().find { value == it.value }
        ?: throw IllegalArgumentException("$value is not a valid signing algorithm.")
}

// to be removed, pending this issue:
// https://github.com/imulab/connect-sdk/issues/4
private fun encryptionAlgorithmTemporaryParseFix(value: String): EncryptionAlgorithm {
    return EncryptionAlgorithm.values().find { value == it.value }
        ?: throw IllegalArgumentException("$value is not a valid encryption algorithm.")
}

// to be removed, pending this issue:
// https://github.com/imulab/connect-sdk/issues/4
private fun encryptionEncodingTemporaryParseFix(value: String): EncryptionEncoding {
    return EncryptionEncoding.values().find { value == it.value }
        ?: throw IllegalArgumentException("$value is not a valid encryption encoding.")
}