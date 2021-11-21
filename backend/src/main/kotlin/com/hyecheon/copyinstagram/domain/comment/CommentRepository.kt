package com.hyecheon.copyinstagram.domain.comment

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */
interface CommentRepository : JpaRepository<Comment, Long> {

	fun findAllByImageId(imageId: Long, pageable: Pageable): Page<Comment>
}