package com.hyecheon.copyinstagram.web.dto

import com.hyecheon.copyinstagram.domain.image.Image
import com.hyecheon.copyinstagram.domain.like.Like
import com.hyecheon.copyinstagram.mapper.ImageMapper
import com.hyecheon.copyinstagram.mapper.UserMapper
import org.mapstruct.factory.Mappers
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
object ImageDto {
	val mapper = Mappers.getMapper(ImageMapper::class.java)

	data class Upload(
		@field:NotNull
		var file: MultipartFile? = null,
		var caption: String? = null
	)

	data class Resp(
		var caption: String? = null,
		var postImageUrl: String? = null,
		var createdAt: LocalDateTime? = null,
		var user: InstaUserDto.UserSimpleResp? = null
	)

	data class StoryResp(
		var id: Long? = null,
		var caption: String? = null,
		var postImageUrl: String? = null,
		var createdAt: LocalDateTime? = null,
		var like: Boolean? = null,
		var likeCnt: Long = 0,
		var user: InstaUserDto.UserSimpleResp? = null
	)

	fun toResp(image: Image) = run {
		val resp = mapper.toResp(image)
		val toInstaDto = Mappers.getMapper(UserMapper::class.java).toUserSimple(image.user)
		resp.user = toInstaDto
		resp
	}

	fun toStoryResp(image: Image, likeImageIds: List<Long>) = run {
		val resp = mapper.toStoryResp(image)
		val toInstaDto = Mappers.getMapper(UserMapper::class.java).toUserSimple(image.user)
		resp.like = likeImageIds.contains(image.id)
		resp.user = toInstaDto
		resp
	}
}