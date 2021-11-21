package com.hyecheon.copyinstagram.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */

@Configuration
class Bootstrap(
	@Value("\${file.path.upload-file}")
	private val uploadDir: String
) {
	private val log = LoggerFactory.getLogger(this::class.java)

	@EventListener(classes = [ApplicationStartedEvent::class])
	fun startedEvent() = run {
		createDir()
		log.info("startup")
	}

	fun createDir() = run {
		val path = Paths.get(uploadDir)
		if (!path.exists()) {
			path.createDirectories()
			log.info("create directories")
		}
	}
}