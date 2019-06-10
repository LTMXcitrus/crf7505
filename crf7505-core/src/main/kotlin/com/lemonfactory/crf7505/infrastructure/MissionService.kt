package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.Mission
import java.time.LocalDateTime

interface MissionService {
    fun getAllMissions(user: PegassUser, start: LocalDateTime, end: LocalDateTime): List<Mission>
}