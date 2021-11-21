package com.hyecheon.copyinstagram.domain.image

import com.hyecheon.copyinstagram.domain.entity.BaseEntity
import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import com.hyecheon.copyinstagram.domain.like.Like
import org.hibernate.annotations.Where
import javax.persistence.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
@Where(clause = "isDeleted = 0")
@Entity
data class Image(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	var caption: String? = null,

	var postImageUrl: String,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	var user: InstaUser,

	@OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
	var likes: MutableList<Like> = mutableListOf(),

	var likeCnt: Long = 0

) : BaseEntity()
