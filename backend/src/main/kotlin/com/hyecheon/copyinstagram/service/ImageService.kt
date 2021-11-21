package com.hyecheon.copyinstagram.service

import com.hyecheon.copyinstagram.domain.image.Image
import com.hyecheon.copyinstagram.domain.image.ImageRepository
import com.hyecheon.copyinstagram.domain.insta_user.InstaUserRepository
import com.hyecheon.copyinstagram.domain.subscribe.SubscribeRepository
import com.hyecheon.copyinstagram.exception.UserIdNotExistsException
import com.hyecheon.copyinstagram.web.api.UploadService
import com.hyecheon.copyinstagram.web.dto.ImageDto
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */

@Transactional
@Service
class ImageService(
	private val imageRepository: ImageRepository,
	private val userRepository: InstaUserRepository,
	private val subscribeRepository: SubscribeRepository,
	private val uploadService: UploadService
) {
	@Transactional
	fun uploadImage(userId: Long, upload: ImageDto.Upload) = run {
		val path = uploadService.uploadFile(userId, upload.file!!)
		val user = userRepository.findById(userId).orElseThrow { UserIdNotExistsException(userId) }
		val image = Image(postImageUrl = path, user = user, caption = upload.caption)
		imageRepository.save(image)
	}

	fun findByUserId(userId: Long, pageable: Pageable) = run {
		val user = userRepository.findById(userId).orElseThrow { UserIdNotExistsException(userId) }
		imageRepository.findByUser(user, pageable)
	}

	fun subscribingImage(loginId: Long, pageable: Pageable) = run {
		val subscribes = subscribeRepository.findByFromUserId(loginId)
		val subscribingUsers = subscribes.map { subScribe -> subScribe.toUser }.requireNoNulls()
		imageRepository.findAllByUserIn(subscribingUsers, pageable)
	}

	fun findAll(pageable: Pageable) = run {
		imageRepository.findAll(pageable)
	}
}