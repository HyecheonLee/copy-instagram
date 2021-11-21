package com.hyecheon.copyinstagram.exception

import com.hyecheon.copyinstagram.web.dto.ResponseDto
import org.hibernate.JDBCException
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@RestControllerAdvice
class ApiException {

	@ExceptionHandler(MethodArgumentNotValidException::class)
	fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException) = run {
		val errorMessage = e.bindingResult.allErrors.first().defaultMessage
		val data = e.bindingResult.fieldErrors.fold(mutableMapOf<String, String>()) { acc, fieldError ->
			acc[fieldError.field] =
				"[${fieldError.field}](은)는 ${fieldError.defaultMessage} 입력된 값: [${fieldError.rejectedValue}]"
			acc
		}
		ResponseDto(
			code = HttpStatus.BAD_REQUEST.value(),
			message = errorMessage,
			data
		)
	}

	@ExceptionHandler(BindException::class)
	fun handleBindException(e: BindException) = run {
		val errorMessage = e.bindingResult.allErrors.first().defaultMessage
		val data = e.bindingResult.fieldErrors.fold(mutableMapOf<String, String>()) { acc, fieldError ->
			acc[fieldError.field] =
				"[${fieldError.field}](은)는 ${fieldError.defaultMessage} 입력된 값: [${fieldError.rejectedValue}]"
			acc
		}
		ResponseDto(
			code = HttpStatus.BAD_REQUEST.value(),
			message = errorMessage,
			data
		)
	}
	@ExceptionHandler(JDBCException::class)
	fun handleConstraintViolationException(e: JDBCException) = run {
		val data = mapOf<String, Any?>(
			"errorCode" to e.sqlException.errorCode,
			"sqlState" to e.sqlException.sqlState,
			"message" to e.sqlException.message
		)
		ResponseDto(
			code = HttpStatus.BAD_REQUEST.value(),
			message = e.sqlException.message,
			data = data
		)
	}

	@ExceptionHandler(UsernameNotExistsException::class)
	fun handleUsernameNotExistsException(e: UsernameNotExistsException) {
		ResponseDto(
			code = HttpStatus.BAD_REQUEST.value(),
			message = e.message,
			data = null
		)
	}

	@ExceptionHandler(TokenVerifyException::class)
	fun handleTokenVerifyException(e: TokenVerifyException) {
		ResponseDto(
			code = HttpStatus.BAD_REQUEST.value(),
			message = e.message,
			data = null
		)
	}

	@ExceptionHandler(Exception::class)
	fun exception(e: Exception) = run {
		ResponseDto(
			code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
			message = e.message,
			data = null
		)
	}
}