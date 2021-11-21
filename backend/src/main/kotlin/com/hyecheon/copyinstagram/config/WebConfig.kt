package com.hyecheon.copyinstagram.config

import com.hyecheon.copyinstagram.web.constant.UriConstant
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.file.Paths


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
//@Profile("local")
@Configuration
class WebConfig(
	@Value("\${file.path.upload-file}")
	private val uploadDir: String
) : WebMvcConfigurer {
	override fun addCorsMappings(registry: CorsRegistry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000")
	}

	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		registry.addResourceHandler("${UriConstant.file}/**")
			.addResourceLocations("file://${Paths.get(uploadDir).toAbsolutePath()}/")
			.setCacheControl(CacheControl.noCache())
		super.addResourceHandlers(registry)
	}
}