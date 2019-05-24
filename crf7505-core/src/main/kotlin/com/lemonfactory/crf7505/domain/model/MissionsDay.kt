package com.lemonfactory.crf7505.domain.model

import java.time.LocalDate

data class MissionsDay(val date: LocalDate,
                       val missions: List<Mission>)