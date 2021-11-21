package com.hyecheon.copyinstagram.config.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.copyinstagram.domain.insta_user.Authority
import com.hyecheon.copyinstagram.utils.AppUtils
import com.hyecheon.copyinstagram.web.dto.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
class JwtFilter(private val objectMapper: ObjectMapper?) : OncePerRequestFilter() {
	companion object {
		private const val AuthorizationHeader = "Authorization"
		private const val BearerPrefix = "Bearer"
	}

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val resolveToken = resolveToken(request)
		if (resolveToken != null) {
			try {
				val verify = JwtProvider.verify(resolveToken)
				if (verify.success) {
					val authentication = getAuthentication(verify)
					SecurityContextHolder.getContext().authentication = authentication
					filterChain.doFilter(request, response)
				} else {
					setErrorResponse(HttpStatus.BAD_REQUEST.value(), response, verify.error, verify)
				}
			} catch (e: Exception) {
				setErrorResponse(HttpStatus.BAD_REQUEST.value(), response, e.message)
			}
		} else {
			filterChain.doFilter(request, response)
		}
	}

	private fun setErrorResponse(code: Int, response: HttpServletResponse, message: String?, data: Any? = null) {
		response.status = HttpStatus.OK.value()
		response.contentType = "application/json;charset=UTF-8"
		val responseDto = ResponseDto(code = code, message = message, data ?: "")
		val responseJson = objectMapper?.writeValueAsString(responseDto)
		response.writer.write(responseJson ?: "")
	}


	private fun getAuthentication(verify: JwtProvider.VerifyResult): Authentication {
		val role = verify.claims["role"]
		val userId = (verify.claims["userId"] ?: "-1").toLong()
		val authorities = mutableListOf(SimpleGrantedAuthority(role))
		val loginPrincipal = LoginPrincipal(verify.username, userId, Authority.from(role))
		return UsernamePasswordAuthenticationToken(loginPrincipal, null, authorities)
	}

	// Request Header 에서 토큰 정보를 꺼내오기
	private fun resolveToken(request: HttpServletRequest): String? {
		val bearerToken: String? = request.getHeader(AuthorizationHeader)
		if (!bearerToken.isNullOrBlank() && bearerToken.startsWith(BearerPrefix)) {
			return bearerToken.substringAfter(BearerPrefix).trim()
		}
		return null
	}
}