package com.hyecheon.copyinstagram.web.dto

import com.hyecheon.copyinstagram.domain.comment.Comment
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */
object CommentDto {
	data class Req(
		@field:NotBlank
		var content: String? = null,
		@field:NotNull
		var imageId: Long? = null
	) {
		fun toEntity() = run {
			Comment(
				content = content,
				imageId = imageId
			)
		}
	}

	data class Resp(
		val id: Long?,
		val content: String?,
		val createdBy: String?,
		val updatedAt: LocalDateTime,
		val imageId: Long?
	)

	fun from(comment: Comment) = run {
		Resp(comment.id, comment.content, comment.createdBy, comment.updatedAt, comment.imageId)
	}
}