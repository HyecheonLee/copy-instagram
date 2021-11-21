package com.hyecheon.copyinstagram.service

import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import com.hyecheon.copyinstagram.domain.insta_user.InstaUserRepository
import com.hyecheon.copyinstagram.exception.UserIdNotExistsException
import com.hyecheon.copyinstagram.exception.UsernameNotExistsException
import com.hyecheon.copyinstagram.web.api.UploadService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
@Service
class UserService(
	private val userRepository: InstaUserRepository,
	private val passwordEncoder: PasswordEncoder,
	private val uploadService: UploadService
) {

	fun userProfile(userId: Long) = run {
		val user = userRepository.findById(userId).orElseThrow { UserIdNotExistsException(userId) }
		user
	}

	fun findById(userId: Long) = run {
		userRepository.findById(userId).orElseThrow { UserIdNotExistsException(userId) }
	}

	@Transactional
	fun modify(username: String, modifyUser: InstaUser) = run {
		val user = userRepository.findByUsername(username).orElseThrow { UsernameNotExistsException(username) }
		modifyUser.passwordEnc(passwordEncoder)
		user.modify(modifyUser)
		user
	}

	@Transactional
	fun uploadProfile(userId: Long, file: MultipartFile) = run {
		val path = uploadService.uploadFile(userId, file)
		val user = userRepository.findById(userId).orElseThrow { UserIdNotExistsException(userId) }
		user.profileImage = path
		user
	}
}