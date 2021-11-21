package com.hyecheon.copyinstagram.domain.like

import com.hyecheon.copyinstagram.domain.image.Image
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/18
 */
interface LikeRepository : JpaRepository<Like, Long> {
	fun deleteByUserIdAndImageId(userId: Long, imageId: Long)

	fun findAllByImageIn(images: List<Image>): List<Like>

	fun findAllByUserIdAndImageIdIn(userId: Long, imageIds: List<Long>): List<Like>
}