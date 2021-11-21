package com.hyecheon.copyinstagram.service

import com.hyecheon.copyinstagram.domain.image.ImageRepository
import com.hyecheon.copyinstagram.domain.like.Like
import com.hyecheon.copyinstagram.domain.like.LikeRepository
import com.hyecheon.copyinstagram.exception.ImageIdNotExistsException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/18
 */
@Transactional(readOnly = true)
@Service
class LikeService(
	private val likeRepository: LikeRepository,
	private val imageRepository: ImageRepository
) {

	@Transactional
	fun like(userId: Long, imageId: Long): Like {
		val foundImage = imageRepository.findById(imageId).orElseThrow { ImageIdNotExistsException(imageId) }
		val savedLike = likeRepository.save(Like(userId = userId, imageId = imageId))
		foundImage.likeCnt = foundImage.likeCnt + 1
		return savedLike
	}

	@Transactional
	fun unlike(userId: Long, imageId: Long) {
		val foundImage = imageRepository.findById(imageId).orElseThrow { ImageIdNotExistsException(imageId) }
		likeRepository.deleteByUserIdAndImageId(userId, imageId)
		foundImage.likeCnt = foundImage.likeCnt - 1
	}

	fun findAll(userId: Long, imageIds: List<Long>): List<Like> {
		return likeRepository.findAllByUserIdAndImageIdIn(userId, imageIds)
	}
}