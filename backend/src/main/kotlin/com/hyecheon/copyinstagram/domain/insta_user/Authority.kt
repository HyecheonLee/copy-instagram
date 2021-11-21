package com.hyecheon.copyinstagram.domain.insta_user

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
enum class Authority(val role: String) {
	User("ROLE_USER"), Admin("ROLE_ADMIN"), Anonymous("ROLE_ANONYMOUS")
	;

	companion object {
		fun from(role: String?) = run {
			when (role) {
				"ROLE_USER" -> User
				"ROLE_ADMIN" -> Admin
				else -> Anonymous
			}
		}
	}
}