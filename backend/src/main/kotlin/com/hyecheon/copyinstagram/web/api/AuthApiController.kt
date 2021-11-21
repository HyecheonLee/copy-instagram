package com.hyecheon.copyinstagram.web.api

import com.hyecheon.copyinstagram.service.AuthService
import com.hyecheon.copyinstagram.web.constant.UriConstant
import com.hyecheon.copyinstagram.web.dto.InstaUserDto
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@RequestMapping(UriConstant.auth)
@RestController
class AuthApiController(
	private val authService: AuthService,
) {
	private val log = LoggerFactory.getLogger(this::class.java)

	@PostMapping("/signIn")
	fun signIn(@Validated @RequestBody signInReq: InstaUserDto.SignInReq) = run {
		val authToken = signInReq.toAuthenticationToken()
		authService.signIn(authToken)
	}

	@PostMapping("/signUp")
	fun signUp(@Validated @RequestBody signUpReq: InstaUserDto.SignUpReq) = run {
		val user = signUpReq.toEntity()
		authService.signUp(user)
	}
}