package com.hyecheon.copyinstagram.config.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.copyinstagram.web.constant.UriConstant
import com.hyecheon.copyinstagram.web.constant.UriConstant.authSignIn
import com.hyecheon.copyinstagram.web.constant.UriConstant.authSignUp
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@EnableWebSecurity
@Configuration
class SecurityConfig() : WebSecurityConfigurerAdapter() {

	override fun configure(http: HttpSecurity) {
		http.csrf().disable()
			// exception handling 할 때 우리가 만든 클래스를 추가
			.exceptionHandling()
			.authenticationEntryPoint(customJwtAuthenticationEntryPoint())
			.accessDeniedHandler(customJwtAccessDeniedHandler())
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(*UriConstant.permittedAll()).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
	}

	@Bean
	fun encode() = run {
		BCryptPasswordEncoder()
	}

	@Bean
	fun jwtFilter(objectMapper: ObjectMapper? = null) = run {
		JwtFilter(objectMapper)
	}

	@Bean
	fun customJwtAuthenticationEntryPoint() = JwtAuthenticationEntryPoint()

	@Bean
	fun customJwtAccessDeniedHandler() = JwtAccessDeniedHandler()

}