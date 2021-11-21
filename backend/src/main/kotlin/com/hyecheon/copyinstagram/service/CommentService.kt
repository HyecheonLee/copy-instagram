package com.hyecheon.copyinstagram.service

import com.hyecheon.copyinstagram.domain.comment.Comment
import com.hyecheon.copyinstagram.domain.comment.CommentRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */
@Transactional(readOnly = true)
@Service
class CommentService(
	private val commentRepository: CommentRepository
) {

	@Transactional
	fun save(comment: Comment) = run {
		commentRepository.save(comment)
	}

	@Transactional
	fun deleteById(commentId: Long) {
		commentRepository.deleteById(commentId)
	}

	fun findAllByImageId(imageId: Long, pageable: Pageable): Page<Comment> {
		return commentRepository.findAllByImageId(imageId, pageable)
	}
}