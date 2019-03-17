package com.lemonfactory.crf7505.domain.model

import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import java.time.LocalDateTime

@Entity
data class Mission(@Id val id: String,
                   val startTime: LocalDateTime,
                   val endTime: LocalDateTime,
                   val ul: String,
                   val name: String,
                   val roles: List<Role>)