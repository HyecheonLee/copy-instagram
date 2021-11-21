package com.hyecheon.copyinstagram.exception

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
class UserIdNotExistsException(userId: Long) : RuntimeException("사용자 아이디 [ $userId ]가 존재하지 않습니다.")