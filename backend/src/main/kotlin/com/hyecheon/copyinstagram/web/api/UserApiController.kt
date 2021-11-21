package com.hyecheon.copyinstagram.web.api

import com.hyecheon.copyinstagram.config.auth.LoginPrincipal
import com.hyecheon.copyinstagram.service.SubscribeService
import com.hyecheon.copyinstagram.service.UserService
import com.hyecheon.copyinstagram.web.constant.UriConstant
import com.hyecheon.copyinstagram.web.dto.InstaUserDto
import com.hyecheon.copyinstagram.web.dto.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
@RequestMapping(UriConstant.user)
@RestController
class UserApiController(
	private val userService: UserService,
	private val subscribeService: SubscribeService
) {

	@GetMapping("/me")
	fun me(@AuthenticationPrincipal loginPrincipal: LoginPrincipal) = run {
		val findUser = userService.findById(loginPrincipal.userId)
		ResponseDto(
			code = HttpStatus.OK.value(),
			data = InstaUserDto.toUserResp(findUser)
		)
	}

	@GetMapping("/{userId}")
	fun profile(@AuthenticationPrincipal loginPrincipal: LoginPrincipal, @PathVariable userId: Long) = run {
		val findUser = userService.findById(userId)
		val isSubscribed = subscribeService.existsByFromUserIdAndToUserId(loginPrincipal.userId, userId)
		ResponseDto(
			code = HttpStatus.OK.value(),
			data = InstaUserDto.UserInfoResp(
				user = InstaUserDto.toUserResp(findUser),
				isSubscribed = isSubscribed,
				isOwner = loginPrincipal.userId == userId
			)
		)
	}

	@PatchMapping
	fun modify(
		@RequestBody modifyReq: InstaUserDto.ModifyReq,
		@AuthenticationPrincipal loginPrincipal: LoginPrincipal
	) = run {
		val modifyUser = modifyReq.toEntity()
		val modifiedUser = userService.modify(loginPrincipal.username, modifyUser)
		ResponseDto(
			code = HttpStatus.OK.value(),
			data = InstaUserDto.toUserResp(modifiedUser)
		)
	}

	@PatchMapping("/profile")
	fun uploadProfileImage(
		@AuthenticationPrincipal loginPrincipal: LoginPrincipal,
		profileImageFile: MultipartFile?
	) = run {
		val modifiedUser = userService.uploadProfile(loginPrincipal.userId, profileImageFile!!)
		ResponseDto(
			code = HttpStatus.OK.value(),
			data = InstaUserDto.toUserResp(modifiedUser)
		)
	}
}