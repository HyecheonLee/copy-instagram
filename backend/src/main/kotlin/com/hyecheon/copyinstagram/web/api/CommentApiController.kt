package com.hyecheon.copyinstagram.web.api

import com.hyecheon.copyinstagram.service.CommentService
import com.hyecheon.copyinstagram.web.constant.UriConstant
import com.hyecheon.copyinstagram.web.dto.CommentDto
import com.hyecheon.copyinstagram.web.dto.ResponseDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */
@RestController
@RequestMapping(UriConstant.comment)
class CommentApiController(
	private val commentService: CommentService
) {

	@GetMapping("/images/{imageId}")
	fun getAllComments(
		@PathVariable imageId: Long,
		@PageableDefault(sort = ["createdAt"], direction = Sort.Direction.DESC)
		pageable: Pageable
	) = run {
		val commentPage = commentService.findAllByImageId(imageId, pageable)
		ResponseDto(
			data = commentPage.map { comment -> CommentDto.from(comment) }
		)
	}

	@PostMapping
	fun save(@Validated @RequestBody req: CommentDto.Req) = run {
		val savedComment = commentService.save(req.toEntity())
		ResponseDto(
			code = HttpStatus.CREATED.value(),
			data = CommentDto.from(savedComment)
		)
	}

	@DeleteMapping("/{commentId}")
	fun delete(@PathVariable commentId: Long) = run {
		commentService.deleteById(commentId)
		ResponseDto(
			code = HttpStatus.NO_CONTENT.value(),
			data = null
		)
	}
}