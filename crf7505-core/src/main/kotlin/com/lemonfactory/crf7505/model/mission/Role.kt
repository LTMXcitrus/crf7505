package com.lemonfactory.crf7505.model.mission

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime

data class Role(val type: RoleType,
                val quantity: Int,
                @JsonFormat(pattern = "HH:mm")
                val beginDate: LocalTime? = null,
                @JsonFormat(pattern = "HH:mm")
                val endDate: LocalTime? = null) {
    fun toDbObject(): DbRole {
        return DbRole(
                type,
                quantity,
                beginDate,
                endDate
        )
    }
}


data class DbRole(
        var type: RoleType = RoleType.PARTICIPANT,
        var quantity: Int = 0,
        @JsonFormat(pattern = "HH:mm")
        var beginDate: LocalTime? = null,
        @JsonFormat(pattern = "HH:mm")
        var endDate: LocalTime? = null
) {
    fun toModel(): Role {
        return Role(
                type,
                quantity,
                beginDate,
                endDate
        )
    }
}