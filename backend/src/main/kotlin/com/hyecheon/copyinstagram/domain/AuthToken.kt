package com.hyecheon.copyinstagram.domain

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
data class AuthToken(
	val username: String,
	val userId: Long,
	val authToken: String,
	val refreshToken: String,
)