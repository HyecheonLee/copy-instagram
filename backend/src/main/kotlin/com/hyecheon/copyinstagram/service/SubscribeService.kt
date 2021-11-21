package com.hyecheon.copyinstagram.service

import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import com.hyecheon.copyinstagram.domain.insta_user.InstaUserRepository
import com.hyecheon.copyinstagram.domain.subscribe.SubScribe
import com.hyecheon.copyinstagram.domain.subscribe.SubscribeRepository
import com.hyecheon.copyinstagram.exception.UserIdNotExistsException
import com.hyecheon.copyinstagram.web.dto.SubscribeDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/10
 */
@Transactional(readOnly = true)
@Service
class SubscribeService(
	private val subscribeRepository: SubscribeRepository,
	private val userRepository: InstaUserRepository
) {

	@Transactional
	fun subscribe(fromUserId: Long, toUserId: Long) = run {
		subscribeRepository.save(SubScribe(fromUserId = fromUserId, toUserId = toUserId))
	}

	@Transactional
	fun unSubscribe(fromUserId: Long, toUserId: Long) = run {
		subscribeRepository.deleteByFromUserIdAndToUserId(fromUserId, toUserId)
	}

	fun existsByFromUserIdAndToUserId(fromUserId: Long, toUserId: Long) = run {
		subscribeRepository.existsByFromUserIdAndToUserId(fromUserId, toUserId)
	}

	fun getSubscribeInfo(loginUserId: Long, userId: Long, pageable: Pageable) = run {
		val subscribePage = subscribeRepository.findAllByFromUserId(userId, pageable)
		val subscribingIds =
			findAllByFromUserIdAndToUserIdIn(
				loginUserId,
				subscribePage.content.mapNotNull { subScribe -> subScribe.toUserId }).map(
				SubScribe::toUserId
			)

		SubscribeDto.Info(
			totalPage = subscribePage.totalPages,
			totalElement = subscribePage.totalElements,
			hasNext = subscribePage.hasNext(),
			hasPrev = subscribePage.hasPrevious(),
			subscribeInfos = subscribePage.content
				.mapNotNull { it.toUser }
				.map {
					SubscribeDto.SubscribeInfo(
						username = it.username,
						profileImage = it.profileImage,
						isSubscribed = subscribingIds.contains(it.id),
						isOwner = it.id == loginUserId,
						createdAt = it.createdAt,
					)
				}
		)
	}


	fun findAllByFromUserIdAndToUserIdIn(fromUserId: Long, toUserIds: List<Long>): List<SubScribe> {
		return subscribeRepository.findAllByFromUserIdAndToUserIdIn(fromUserId, toUserIds)
	}
}