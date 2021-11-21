package com.hyecheon.copyinstagram.domain.insta_user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
interface InstaUserRepository : JpaRepository<InstaUser, Long> {
	fun findByUsername(username: String): Optional<InstaUser>
	fun findByIdIn(ids: List<Long>): MutableList<InstaUser>
}