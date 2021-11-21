package com.hyecheon.copyinstagram.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.copyinstagram.config.auth.LoginPrincipal
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/11
 */
@Component
class AppUtils : ApplicationContextAware {
	companion object {
		lateinit var applicationContext: ApplicationContext
		fun <T> getBean(clazz: Class<T>) = run {
			if (this::applicationContext.isInitialized) {
				applicationContext.getBean(clazz)
			} else {
				null
			}
		}

		fun objectMapper() = run {
			val objectMapper = getBean(ObjectMapper::class.java)
			objectMapper ?: ObjectMapper()
		}

		fun loginUsername(): Optional<String> {
			val securityContext = SecurityContextHolder.getContext()
			val authentication = securityContext.authentication ?: return Optional.empty()
			val loginPrincipal = authentication.principal
			return if (loginPrincipal is LoginPrincipal) Optional.of(loginPrincipal.username)
			else Optional.empty()
		}
	}

	override fun setApplicationContext(applicationContext: ApplicationContext) {
		AppUtils.applicationContext = applicationContext
	}

}