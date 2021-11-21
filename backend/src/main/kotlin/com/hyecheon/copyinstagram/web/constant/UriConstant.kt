package com.hyecheon.copyinstagram.web.constant

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
object UriConstant {
	private const val defaultPrefix = "/api/v1"

	const val file = "/file"

	const val auth = "${defaultPrefix}/auth"
	const val authSignIn = "${auth}/signIn"
	const val authSignUp = "${auth}/signUp"

	const val image = "${defaultPrefix}/images"

	const val user = "${defaultPrefix}/users"

	const val subscribe = "${defaultPrefix}/subscribes"

	const val comment = "${defaultPrefix}/comments"

	fun permittedAll() = run {
		arrayOf(authSignIn, authSignUp)
	}
}