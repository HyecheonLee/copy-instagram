package com.hyecheon.copyinstagram.domain.comment

import com.hyecheon.copyinstagram.domain.entity.BaseEntity
import com.hyecheon.copyinstagram.domain.entity.BaseUserEntity
import com.hyecheon.copyinstagram.domain.image.Image
import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import javax.persistence.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */
@Entity
data class Comment(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	@Column(length = 100, nullable = false)
	var content: String? = null,

	@JoinColumn(name = "createdBy", referencedColumnName = "username", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	var user: InstaUser? = null,

	@JoinColumn(name = "imageId", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	var image: Image? = null,
	@Column(nullable = false)
	var imageId: Long?


) : BaseUserEntity()