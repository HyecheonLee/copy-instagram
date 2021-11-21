package com.hyecheon.copyinstagram.domain.insta_user

import com.hyecheon.copyinstagram.domain.entity.BaseEntity
import com.hyecheon.copyinstagram.mapper.UserMapper
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Where
import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@DynamicUpdate(true)
@Entity
@Where(clause = "isDeleted = 0")
data class InstaUser(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	@Column(nullable = false, unique = true, length = 31)
	var username: String? = null,

	@Column(nullable = false)
	var password: String? = null,

	@Column(nullable = false)
	var name: String? = null,

	@Column(nullable = false, unique = true)
	var email: String? = null,

	var website: String? = null,

	var bio: String? = null,

	var phone: String? = null,

	@Enumerated(EnumType.STRING)
	var gender: Gender? = Gender.None,

	@Lob
	var profileImage: String? = null,

	@Enumerated(EnumType.STRING)
	var authority: Authority? = Authority.User
) : BaseEntity() {

	fun passwordEnc(passwordEncoder: PasswordEncoder) = run {
		if (password != null) {
			password = passwordEncoder.encode(password)
		}
	}

	fun modify(modifyUser: InstaUser) {
		val mapper = Mappers.getMapper(UserMapper::class.java)
		mapper.modify(modifyUser, this)
	}
}