package com.lemonfactory.covid

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Dispo(
        val email: String,
        val phone: String,
        val firstname: String,
        val lastname: String,
        val ul: String,
        val vulnerable: String,
        val roleInUl: String,
        val competences: List<String>,
        val profession: String,
        val uniformeStatus: String,
        val creneaux: List<Creneau>,
        val creneauxRepresentations: List<String>
)

data class Creneau(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        val start: LocalDateTime,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        val end: LocalDateTime
)

data class CreneauSearch(
        val date: String,
        val start: Int?,
        val end: Int?
) {
    fun toCreneau(): Creneau {
        return Creneau(
                LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).withHour(start ?: 0),
                end?.let { LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).withHour(it) }
                        ?: LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).withHour(23).withMinute(59)
        )
    }
}

data class DispoSearch(
        val firstname: String? = null,
        val lastname: String? = null,
        val ul: String? = null,
        val competences: List<String>? = null,
        val creneau: CreneauSearch? = null
)