package com.lemonfactory.crf7505.repository

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.ActivityGroup
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.infrastructure.ConnectedUserResolver
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.infrastructure.MissionService
import java.time.LocalDateTime

class MissionRepositoryImpl(private val missionService: MissionService, private val connectedUserResolver: ConnectedUserResolver) : MissionRepository {

    override fun getActivities(user: PegassUser, begin: LocalDateTime, end: LocalDateTime): Activities {
        val userStructure = connectedUserResolver.resolveConnectedUser()?.userStructure
        return Activities(
                retrieveLocalActivities(user, begin, end, userStructure),
                retrieveExternalActivities(user, begin, end, userStructure),
                userStructure
        )
    }

    private fun retrieveLocalActivities(user: PegassUser, begin: LocalDateTime, end: LocalDateTime, userStructure: String?): List<Mission> {
        if(userStructure != null) {
            return missionService.getActivitiesForStructure(user, begin, end, userStructure)
                    .filter { mission -> mission.beginDate.isAfter(begin) && mission.beginDate.isBefore(end) }
                    .filter { mission -> mission.activityGroup != ActivityGroup.AS }
        }
        return emptyList()
    }

    private fun retrieveExternalActivities(user: PegassUser, begin: LocalDateTime, end: LocalDateTime, userStructure: String?): List<Mission> {
        return missionService.getAllMissions(user, begin, end)
                .filter { mission -> mission.beginDate.isAfter(begin) && mission.beginDate.isBefore(end) }
                .filter { mission ->
                    mission.missingRoles.isNotEmpty()
                            || mission.hasCommentedInscriptions
                            || mission.hasModifiedHoursInscriptions
                }
                .filter { mission -> userStructure == null || mission.ul != userStructure }
    }

}
