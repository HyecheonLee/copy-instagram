package com.hyecheon.copyinstagram.exception

import java.lang.RuntimeException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/11
 */
class TokenVerifyException(message: String?) : RuntimeException(message)