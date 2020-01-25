package com.lemonfactory.crf7505.model.mission

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Inscription(val beginDate: LocalDateTime,
                       val endDate: LocalDateTime,
                       val roleType: RoleType,
                       val hasComments: Boolean = false,
                       val hasModifiedHours: Boolean = false,
                       val nivol: String) {
    fun toDbObject(): DbInscription {
        return DbInscription(
                beginDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                endDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                roleType,
                hasComments,
                hasModifiedHours,
                nivol
        )
    }
}

data class DbInscription(
        var beginDate: String = "",
        var endDate: String = "",
        var roleType: RoleType = RoleType.PARTICIPANT,
        var hasComments: Boolean = false,
        var hasModifiedHours: Boolean = false,
        var nivol: String = ""
) {
    fun toModel(): Inscription {
        return Inscription(
                LocalDateTime.parse(beginDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                roleType,
                hasComments,
                hasModifiedHours,
                nivol
        )
    }
}