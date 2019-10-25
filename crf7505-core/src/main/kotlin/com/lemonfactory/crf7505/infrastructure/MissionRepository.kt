package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.PegassUser
import java.time.LocalDateTime

interface MissionRepository {
    fun getActivities(user: PegassUser, begin: LocalDateTime, end: LocalDateTime): Activities
}
