package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.Mission
import java.time.LocalDateTime

interface MissionRepository {
    fun getMissions(user: PegassUser, begin: LocalDateTime, end: LocalDateTime): List<Mission>
}
