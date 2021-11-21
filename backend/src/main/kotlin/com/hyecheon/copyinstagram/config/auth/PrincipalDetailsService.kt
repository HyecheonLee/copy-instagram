package com.hyecheon.copyinstagram.config.auth

import com.hyecheon.copyinstagram.domain.insta_user.InstaUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@Service
class PrincipalDetailsService(
	private val instaInstaUserRepository: InstaUserRepository
) : UserDetailsService {

	override fun loadUserByUsername(username: String): UserDetails {
		val instaUser =
			instaInstaUserRepository.findByUsername(username).orElseThrow { UsernameNotFoundException(username) }
		return InstaUserDetails(instaUser)
	}
}