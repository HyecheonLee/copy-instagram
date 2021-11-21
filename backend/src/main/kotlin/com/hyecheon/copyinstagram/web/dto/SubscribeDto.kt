package com.hyecheon.copyinstagram.web.dto

import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import com.hyecheon.copyinstagram.domain.subscribe.SubScribe
import java.time.LocalDateTime

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/14
 */
object SubscribeDto {
	data class Resp(
		val fromUser: InstaUserDto,
		val toUser: InstaUserDto
	)

	data class SubscribeInfo(
		val username: String?,
		val profileImage: String?,
		val isSubscribed: Boolean,
		val isOwner: Boolean,
		val createdAt: LocalDateTime
	)

	data class Info(
		val subscribeInfos: List<SubscribeInfo> = mutableListOf(),
		val totalPage: Int,
		val totalElement: Long,
		val hasNext: Boolean,
		val hasPrev: Boolean
	)
}