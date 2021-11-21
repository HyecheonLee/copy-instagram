package com.hyecheon.copyinstagram.config.auth

import com.hyecheon.copyinstagram.domain.insta_user.Authority


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
data class LoginPrincipal(
	val username: String,
	val userId: Long,
	val authority: Authority? = null
)