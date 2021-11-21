package com.hyecheon.copyinstagram.web.api

import com.hyecheon.copyinstagram.utils.DateUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.pathString

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/20
 */
@Service
class UploadService(
	@Value("\${file.path.upload-file}")
	private val uploadPath: String
) {
	@Transactional
	fun uploadFile(userId: Long, file: MultipartFile) = run {
		val prefixDir = Paths.get("${uploadPath}/${userId}/${DateUtils.nowDate()}")
		if (!prefixDir.exists()) prefixDir.createDirectories()
		val path = Paths.get("${prefixDir.pathString}/${UUID.randomUUID()}_${file.originalFilename}")
		file.transferTo(path)
		path.pathString.replace(uploadPath, "")
	}
}