package com.hyecheon.copyinstagram.web.api

import com.hyecheon.copyinstagram.aop.LogExecutionTime
import com.hyecheon.copyinstagram.config.auth.LoginPrincipal
import com.hyecheon.copyinstagram.service.ImageService
import com.hyecheon.copyinstagram.service.LikeService
import com.hyecheon.copyinstagram.web.constant.UriConstant
import com.hyecheon.copyinstagram.web.dto.ImageDto
import com.hyecheon.copyinstagram.web.dto.LikeDto
import com.hyecheon.copyinstagram.web.dto.ResponseDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
@RequestMapping(UriConstant.image)
@RestController
class ImageApiController(
	private val imageService: ImageService,
	private val likeService: LikeService
) {

	@PostMapping
	fun imageUpload(
		@Validated upload: ImageDto.Upload,
		@AuthenticationPrincipal loginPrincipal: LoginPrincipal
	) = run {
		imageService.uploadImage(loginPrincipal.userId, upload)
	}

	@GetMapping("/users/{userId}")
	fun userImages(
		@PathVariable userId: Long,
		@PageableDefault(
			sort = ["id"],
			direction = Sort.Direction.DESC
		) pageable: Pageable
	) = run {
		imageService.findByUserId(userId, pageable)
			.map { image -> ImageDto.toResp(image) }
	}

	@GetMapping("/stories")
	fun imageStory(
		@AuthenticationPrincipal loginPrincipal: LoginPrincipal,
		@PageableDefault(sort = ["id"], direction = Sort.Direction.DESC, size = 30)
		pageable: Pageable
	) = run {
		val subscribingImage = imageService.subscribingImage(loginPrincipal.userId, pageable)
		val likeImageIds = likeService.findAll(loginPrincipal.userId, subscribingImage.content
			.mapNotNull { it.id })
			.map { it.imageId }
		ResponseDto(data = subscribingImage.map { image -> ImageDto.toStoryResp(image, likeImageIds) })
	}

	@PostMapping("/{imageId}/like")
	fun like(
		@AuthenticationPrincipal loginPrincipal: LoginPrincipal,
		@PathVariable imageId: Long
	) = run {
		likeService.like(loginPrincipal.userId, imageId)
		ResponseDto(code = HttpStatus.CREATED.value(), data = null)
	}

	@DeleteMapping("/{imageId}/like")
	fun unlike(
		@AuthenticationPrincipal loginPrincipal: LoginPrincipal,
		@PathVariable imageId: Long
	) = run {
		likeService.unlike(loginPrincipal.userId, imageId)
		ResponseDto(code = HttpStatus.NO_CONTENT.value(), data = null)
	}
}