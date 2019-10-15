package com.lemonfactory.crf7505.domain.model.mission

import java.time.LocalDate
import java.time.LocalDateTime

data class Mission(val id: String,
                   val beginDate: LocalDateTime,
                   val endDate: LocalDateTime,
                   val name: String,
                   val ul: String,
                   val inscriptions: List<Inscription>,
                   val roles: List<Role>,
                   val missingRoles: List<Role>,
                   val hasCommentedInscriptions: Boolean = false,
                   val hasModifiedHoursInscriptions: Boolean = false,
                   val date: LocalDate = beginDate.toLocalDate())
