package com.hyecheon.copyinstagram.web.dto

import javax.validation.constraints.NotNull

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/18
 */
object LikeDto {
	data class Req(
		@field:NotNull
		var imageId: Long? = null
	)
}