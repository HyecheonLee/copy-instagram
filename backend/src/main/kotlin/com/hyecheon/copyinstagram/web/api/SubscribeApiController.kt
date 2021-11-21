package com.hyecheon.copyinstagram.web.api

import com.hyecheon.copyinstagram.config.auth.LoginPrincipal
import com.hyecheon.copyinstagram.domain.subscribe.SubScribe
import com.hyecheon.copyinstagram.service.SubscribeService
import com.hyecheon.copyinstagram.web.constant.UriConstant
import com.hyecheon.copyinstagram.web.dto.ResponseDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/10
 */
@RestController
@RequestMapping(value = [UriConstant.subscribe])
class SubscribeApiController(
	private val subscribeService: SubscribeService
) {

	@GetMapping("/{toUserId}")
	fun subscribeInfo(
		@AuthenticationPrincipal loginPrincipal: LoginPrincipal,
		@PathVariable toUserId: Long,
		@PageableDefault(sort = ["id"], direction = Sort.Direction.DESC)
		pageable: Pageable
	) = run {
		val subscribeInfo = subscribeService.getSubscribeInfo(loginPrincipal.userId, toUserId, pageable)
		ResponseDto(code = HttpStatus.OK.value(), data = subscribeInfo)
	}

	@PostMapping("/{toUserId}")
	fun subscribe(@AuthenticationPrincipal loginPrincipal: LoginPrincipal, @PathVariable toUserId: Long) = run {
		val savedSubscribe = subscribeService.subscribe(loginPrincipal.userId, toUserId)
		ResponseDto(
			code = HttpStatus.CREATED.value(),
			message = "success",
			data = savedSubscribe
		)
	}

	@DeleteMapping("/{toUserId}")
	fun unSubscribe(@AuthenticationPrincipal loginPrincipal: LoginPrincipal, @PathVariable toUserId: Long) = run {
		subscribeService.unSubscribe(loginPrincipal.userId, toUserId)
		ResponseDto<Any>(
			code = HttpStatus.NO_CONTENT.value(),
		)
	}
}