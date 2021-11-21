package com.hyecheon.copyinstagram.mapper

import com.hyecheon.copyinstagram.domain.image.Image
import com.hyecheon.copyinstagram.web.dto.ImageDto
import org.mapstruct.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ImageMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "user", ignore = true)
	fun toResp(image: Image): ImageDto.Resp

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "user", ignore = true)
	fun toStoryResp(image: Image): ImageDto.StoryResp
}