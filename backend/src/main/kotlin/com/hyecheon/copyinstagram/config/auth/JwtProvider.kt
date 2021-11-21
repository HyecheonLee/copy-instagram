package com.hyecheon.copyinstagram.config.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import org.slf4j.LoggerFactory
import java.time.Instant

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/11
 */
class JwtProvider {
	companion object {
		private val log = LoggerFactory.getLogger(this::class.java)
		private val algorithm: Algorithm = Algorithm.HMAC256("hyecheon")

		private const val AuthTime = 60 * 60 * 24 * 30
		private const val RefreshTime = 60 * 60 * 24 * 7
		fun generateAuthToken(user: InstaUser): String = run {
			JWT.create()
				.withSubject(user.username)
				.withClaim("userId", user.id)
				.withClaim("role", user.authority?.role)
				.withClaim("exp", Instant.now().epochSecond + AuthTime)
				.sign(algorithm)
		}

		fun generateRefreshToken(user: InstaUser): String = run {
			JWT.create()
				.withSubject(user.username)
				.withClaim("userId", user.id)
				.withClaim("role", user.authority?.role)
				.withClaim("exp", Instant.now().epochSecond + RefreshTime)
				.sign(algorithm)
		}

		fun verify(token: String) = run {
			try {
				val verify = JWT.require(algorithm).build().verify(token)
				val claims = verify.claims.mapValues { entry -> entry.value.toString() }
				VerifyResult(verify.subject, success = true, claims)
			} catch (e: TokenExpiredException) {
				val decode = JWT.decode(token)
				VerifyResult(decode.subject, false, error = e.message)
			} catch (e: Exception) {
				log.error("verify {}", e.message, e)
				val decode = JWT.decode(token)
				VerifyResult(decode.subject, false, error = e.message)
			}
		}

	}

	data class VerifyResult(
		val username: String,
		val success: Boolean = false,
		val claims: Map<String, String> = mutableMapOf(),
		val error: String? = null
	)
}