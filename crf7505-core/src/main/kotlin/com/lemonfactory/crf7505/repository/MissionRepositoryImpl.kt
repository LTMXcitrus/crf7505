package com.lemonfactory.crf7505.repository

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.infrastructure.MissionService
import java.time.LocalDateTime

class MissionRepositoryImpl(private val missionService: MissionService) : MissionRepository {

    override fun getMissionsByDay(user: PegassUser, begin: LocalDateTime, end: LocalDateTime): List<MissionsDay> {
        return missionService.getAllMissions(user, begin, end)
                .filter { mission -> mission.beginDate.isAfter(begin) && mission.beginDate.isBefore(end)  }
                .groupBy { mission -> mission.beginDate.toLocalDate() }
                .map { entry -> MissionsDay(entry.key, entry.value) }
    }

}