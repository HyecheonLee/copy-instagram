package com.hyecheon.copyinstagram.domain.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */
@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseUserEntity(

	@CreatedBy
	@Column(updatable = false)
	var createdBy: String? = null,

	@LastModifiedBy
	var updatedBy: String? = null,
) : BaseEntity()