package com.hyecheon.copyinstagram.config.auth

import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
class InstaUserDetails(
	val user: InstaUser
) : UserDetails {
	override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
		return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority(user.authority?.role))
	}

	override fun getPassword(): String {
		return user.password!!
	}

	override fun getUsername(): String {
		return user.username!!
	}

	override fun isAccountNonExpired(): Boolean {
		return !user.deleted
	}

	override fun isAccountNonLocked(): Boolean {
		return !user.deleted
	}

	override fun isCredentialsNonExpired(): Boolean {
		return !user.deleted
	}

	override fun isEnabled(): Boolean {
		return !user.deleted
	}
}