package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.Mission
import java.time.LocalDateTime

interface MissionService {
    fun getExternalMissions(user: PegassUser, start: LocalDateTime, end: LocalDateTime, structure: String?): List<Mission>
    fun getActivitiesForStructure(user: PegassUser, start: LocalDateTime, end: LocalDateTime, structure: String): List<Mission>

    fun terminate()
}
