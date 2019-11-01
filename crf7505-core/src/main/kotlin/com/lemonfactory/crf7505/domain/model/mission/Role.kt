package com.lemonfactory.crf7505.domain.model.mission

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime

data class Role(val type: RoleType,
                val quantity: Int,
                @JsonFormat(pattern = "HH:mm")
                val beginDate: LocalTime? = null,
                @JsonFormat(pattern = "HH:mm")
                val endDate: LocalTime? = null)