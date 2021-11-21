package com.hyecheon.copyinstagram.exception

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
class UsernameNotExistsException(val username: String) : RuntimeException("${username}이 존재하지 않습니다.")