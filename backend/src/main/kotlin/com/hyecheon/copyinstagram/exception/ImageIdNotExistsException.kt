package com.hyecheon.copyinstagram.exception

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/18
 */
class ImageIdNotExistsException(imageId: Long) : RuntimeException("이미지 아이디 [ $imageId ]가 존재하지 않습니다.")