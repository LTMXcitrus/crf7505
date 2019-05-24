package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.Mission
import com.lemonfactory.crf7505.domain.model.MissionsDay
import com.lemonfactory.crf7505.domain.model.PegassUser
import java.time.LocalDate

interface MissionService {
    fun getAllMissions(user: PegassUser, start: LocalDate, end: LocalDate): List<MissionsDay>
}