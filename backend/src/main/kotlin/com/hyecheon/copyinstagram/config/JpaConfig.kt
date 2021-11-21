package com.hyecheon.copyinstagram.config

import com.hyecheon.copyinstagram.utils.AppUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@EnableJpaAuditing
@Configuration
class JpaConfig {
	@Bean
	fun auditorProvider(): AuditorAware<String> {
		return AuditorAware { AppUtils.loginUsername() }
	}
}