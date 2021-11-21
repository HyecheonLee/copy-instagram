package com.hyecheon.copyinstagram.domain.like

import com.hyecheon.copyinstagram.domain.entity.BaseEntity
import com.hyecheon.copyinstagram.domain.image.Image
import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import javax.persistence.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/18
 */

@Entity
@Table(
	name = "Likes",
	uniqueConstraints = [UniqueConstraint(name = "likes_uk", columnNames = ["imageId", "userId"])]
)
data class Like(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imageId", insertable = false, updatable = false)
	var image: Image? = null,

	@Column(nullable = false)
	var imageId: Long,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	var user: InstaUser? = null,

	@Column(nullable = false)
	var userId: Long,

	) : BaseEntity()