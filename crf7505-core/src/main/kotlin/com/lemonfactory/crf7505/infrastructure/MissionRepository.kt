package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import java.time.LocalDateTime

interface MissionRepository {
    fun getMissionsByDay(user: PegassUser, begin: LocalDateTime, end: LocalDateTime): List<MissionsDay>
}