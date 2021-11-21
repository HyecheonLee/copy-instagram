package com.hyecheon.copyinstagram.mapper


import com.hyecheon.copyinstagram.domain.insta_user.InstaUser
import com.hyecheon.copyinstagram.web.dto.InstaUserDto
import org.mapstruct.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	fun modify(source: InstaUser, @MappingTarget target: InstaUser)

	fun toInstaDto(user: InstaUser): InstaUserDto.UserResp
	fun toUserSimple(user: InstaUser): InstaUserDto.UserSimpleResp
}