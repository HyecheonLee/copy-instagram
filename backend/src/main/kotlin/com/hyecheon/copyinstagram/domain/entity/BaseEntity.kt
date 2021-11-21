package com.hyecheon.copyinstagram.domain.entity

import org.hibernate.annotations.Where
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/08
 */
@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity(

	@CreatedDate
	@Column(updatable = false)
	var createdAt: LocalDateTime = LocalDateTime.now(),

	@LastModifiedDate
	var updatedAt: LocalDateTime = LocalDateTime.now(),

	@Column(name = "isDeleted", nullable = false)
	var deleted: Boolean = false

)