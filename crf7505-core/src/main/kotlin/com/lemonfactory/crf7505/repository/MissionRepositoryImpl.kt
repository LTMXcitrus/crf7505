package com.lemonfactory.crf7505.repository

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.infrastructure.MissionService
import java.time.LocalDateTime

class MissionRepositoryImpl(private val missionService: MissionService) : MissionRepository {

    override fun getMissions(user: PegassUser, begin: LocalDateTime, end: LocalDateTime): List<Mission> {
        return missionService.getAllMissions(user, begin, end)
                .filter { mission -> mission.beginDate.isAfter(begin) && mission.beginDate.isBefore(end)  }
                .filter { mission -> mission.missingRoles.isNotEmpty()}
    }

}
