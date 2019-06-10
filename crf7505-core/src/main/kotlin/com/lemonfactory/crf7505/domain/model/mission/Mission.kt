package com.lemonfactory.crf7505.domain.model.mission

import java.time.LocalDateTime

data class Mission(val beginDate: LocalDateTime,
                   val endDate: LocalDateTime,
                   val name: String,
                   val ul: String,
                   val inscriptions: List<Inscription>,
                   val roles: List<Role>)