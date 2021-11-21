package com.hyecheon.copyinstagram.domain.subscribe

import com.hyecheon.copyinstagram.domain.entity.BaseEntity
import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import org.hibernate.annotations.Where
import javax.persistence.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/10
 */
@Where(clause = "isDeleted = 0")
@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "subscribe_uk", columnNames = ["fromUserId", "toUserId"])])
data class SubScribe(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fromUserId", updatable = false, insertable = false)
	var fromUser: InstaUser? = null,
	var fromUserId: Long,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "toUserId", updatable = false, insertable = false)
	var toUser: InstaUser? = null,
	var toUserId: Long
) : BaseEntity() {

}