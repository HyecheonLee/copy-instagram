package com.hyecheon.copyinstagram.domain.image

import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
interface ImageRepository : JpaRepository<Image, Long> {
	fun findByUser(user: InstaUser, pageable: Pageable): Page<Image>

	fun findByUserId(userId: Long, pageable: Pageable): Page<Image>

	fun findAllByUserIn(users: List<InstaUser>, pageable: Pageable): Page<Image>

	@Query("update Image i set i.likeCnt = i.likeCnt+1 where i.id = :imageId")
	fun incLikeCnt(imageId: Long)

	@Query("update Image i set i.likeCnt = i.likeCnt-1 where i.id = :imageId")
	fun decLikeCnt(imageId: Long)
}