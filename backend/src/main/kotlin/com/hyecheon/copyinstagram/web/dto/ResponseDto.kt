package com.hyecheon.copyinstagram.web.dto

import java.time.LocalDateTime

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
data class ResponseDto<T>(
	val code: Int = 200,
	val message: String? = "success",
	val data: T? = null,
	val timestamp: LocalDateTime = LocalDateTime.now()
)
