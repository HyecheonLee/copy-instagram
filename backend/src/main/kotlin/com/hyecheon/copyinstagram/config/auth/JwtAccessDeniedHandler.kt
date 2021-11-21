package com.hyecheon.copyinstagram.config.auth

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {
	override fun handle(
		request: HttpServletRequest,
		response: HttpServletResponse,
		accessDeniedException: AccessDeniedException
	) {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
}