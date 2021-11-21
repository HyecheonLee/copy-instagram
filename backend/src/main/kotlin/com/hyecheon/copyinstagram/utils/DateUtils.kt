package com.hyecheon.copyinstagram.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */
object DateUtils {
	private val ZonedNow = { ZonedDateTime.now(ZoneId.of("Asia/Seoul")) }

	fun nowDate(pattern: String = "yyyy-MM-dd"): String =
		ZonedNow().toLocalDate().format(DateTimeFormatter.ofPattern(pattern))

	fun nowToLocalDateTime(): LocalDateTime = ZonedNow().toLocalDateTime()
}