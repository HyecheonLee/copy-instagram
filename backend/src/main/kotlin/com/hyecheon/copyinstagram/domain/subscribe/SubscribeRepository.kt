package com.hyecheon.copyinstagram.domain.subscribe

import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/10
 */
interface SubscribeRepository : JpaRepository<SubScribe, Long> {
	fun deleteByFromUserIdAndToUserId(fromUserId: Long, toUserId: Long)
	fun findByFromUser(fromUser: InstaUser, pageable: Pageable): Page<SubScribe>

	@Query(value = "select s from SubScribe s join s.toUser where s.fromUserId = :fromUserId")
	fun findAllByFromUserId(fromUserId: Long, pageable: Pageable): Page<SubScribe>

	fun findByFromUserId(fromUserId: Long): List<SubScribe>
	fun existsByFromUserAndToUser(fromUser: InstaUser, toUser: InstaUser): Boolean
	fun existsByFromUserIdAndToUserId(fromUserId: Long, toUserId: Long): Boolean

	@Query(value = "select s from SubScribe s join fetch s.toUser where s.fromUser = :fromUser and s.toUser.id in (:toUserIds)")
	fun findAllByFromUserAndToUserIdIn(fromUser: InstaUser, toUserIds: List<Long>): List<SubScribe>

	fun findAllByFromUserIdAndToUserIdIn(fromUserId: Long, toUserIds: List<Long>): List<SubScribe>
}