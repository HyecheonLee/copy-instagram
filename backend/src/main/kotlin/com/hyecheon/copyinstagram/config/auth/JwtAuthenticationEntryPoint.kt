package com.hyecheon.copyinstagram.config.auth

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
	override fun commence(
		request: HttpServletRequest,
		response: HttpServletResponse,
		authException: AuthenticationException
	) {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}