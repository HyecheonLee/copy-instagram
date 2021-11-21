package com.hyecheon.copyinstagram.web.dto

import com.hyecheon.copyinstagram.domain.insta_user.Authority
import com.hyecheon.copyinstagram.domain.insta_user.Gender
import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import com.hyecheon.copyinstagram.mapper.UserMapper
import org.apache.tomcat.util.codec.binary.Base64
import org.mapstruct.factory.Mappers
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
object InstaUserDto {
	private val userMapper = Mappers.getMapper(UserMapper::class.java)

	data class ModifyProfileImage(
		var profileImageFile: MultipartFile?
	)

	data class ModifyReq(
		var password: String? = null,
		var email: String? = null,
		var website: String? = null,
		var bio: String? = null,
		var phone: String? = null,
		var gender: Gender? = null,
		var profileImageFile: MultipartFile? = null,
		var authority: Authority? = null,
	) {
		fun toEntity() = run {
			val profileImage =
				profileImageFile?.let { "data:image/png;base64,${String(Base64.encodeBase64(profileImageFile?.bytes))}" }

			InstaUser(
				password = password,
				email = email,
				website = website,
				bio = bio,
				phone = phone,
				gender = gender,
				profileImage = profileImage,
				authority = authority
			)
		}
	}

	data class SignInReq(
		@field:NotBlank
		val username: String? = null,
		@field:NotBlank
		val password: String? = null
	) {
		fun toAuthenticationToken() = run {
			UsernamePasswordAuthenticationToken(username, password)
		}
	}

	data class SignUpReq(
		@field:NotBlank
		val username: String? = null,

		@field:NotBlank
		val password: String? = null,

		@field:NotBlank
		@field:Email
		val email: String? = null,

		@field:NotBlank
		val name: String? = null
	) {
		fun toEntity() = run {
			InstaUser(
				username = username!!,
				password = password!!,
				email = email!!,
				name = name!!
			)
		}
	}

	data class UserResp(
		var id: Long,
		var username: String? = null,
		var name: String? = null,
		var email: String? = null,
		var website: String? = null,
		var bio: String? = null,
		var phone: String? = null,
		var gender: Gender? = Gender.None,
		var profileImage: String? = null,
		var authority: Authority? = Authority.User
	)

	data class UserSimpleResp(
		var id: Long,
		var username: String? = null,
		var name: String? = null,
	)

	data class UserInfoResp(
		val user: UserResp,
		val isOwner: Boolean,
		val isSubscribed: Boolean,
	)


	fun toUserResp(instaUser: InstaUser) = run {
		userMapper.toInstaDto(instaUser)
	}
}