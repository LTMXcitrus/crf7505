package com.lemonfactory.crf7505.domain.model.mission

import java.time.LocalDateTime

data class Inscription(val beginDate: LocalDateTime,
                       val endDate: LocalDateTime,
                       val roleType: RoleType)