package com.hyecheon.copyinstagram.service

import com.hyecheon.copyinstagram.config.auth.InstaUserDetails
import com.hyecheon.copyinstagram.config.auth.JwtProvider
import com.hyecheon.copyinstagram.domain.AuthToken
import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import com.hyecheon.copyinstagram.domain.insta_user.InstaUserRepository
import com.hyecheon.copyinstagram.exception.UsernameNotExistsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@Service
@Transactional(readOnly = true)
class AuthService(
	private val instaUserRepository: InstaUserRepository,
	private val passwordEncoder: BCryptPasswordEncoder,
	private val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {
	@Transactional
	fun signUp(instaUser: InstaUser) = run {
		instaUser.passwordEnc(passwordEncoder)
		instaUserRepository.save(instaUser)
	}

	fun signIn(authenticationToken: UsernamePasswordAuthenticationToken) = run {
		val authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
		val principal = authentication.principal
		if (principal is InstaUserDetails) {
			return@run AuthToken(
				username = principal.username,
				userId = principal.user.id!!,
				authToken = JwtProvider.generateAuthToken(principal.user),
				refreshToken = JwtProvider.generateRefreshToken(principal.user)
			)
		}
		throw RuntimeException("principal is not InstaUserDetails")
	}
}