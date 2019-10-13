package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import com.lemonfactory.crf7505.domain.model.mission.Role
import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.crf7505.domain.model.mission.RoleType.*

class MissionFilter {

    private val defaultInterests = mapOf(
            CI to listOf(PSE2, CI),
            PSE2 to listOf(PSE1, PSE2),
            CH_VPSP to listOf(CH_VPSP, PSE2, PSE1),
            PSE1 to listOf(PSE1),
            PSC1 to listOf(PSC1)
    )


    fun filter(missionsDays: List<MissionsDay>, volunteer: Volunteer): List<MissionsDay> {
        return missionsDays.map { day -> MissionsDay(day.date, filterMissions(day.missions, volunteer)) }
    }

    private fun filterMissions(missions: List<Mission>, volunteer: Volunteer): List<Mission> {
        if(volunteer.getInterests().isEmpty()) {
            return missions.filter { mission -> hasMatchingRole(mission.missingRoles, defaultInterests[volunteer.role]) }
        }
        return missions.filter { mission -> hasMatchingRole(mission.missingRoles, volunteer.getInterests()) }
    }

    private fun hasMatchingRole(missingRoles: List<Role>, interestedIn: List<RoleType>?) =
            missingRoles.any { missingRole -> interestedIn?.contains(missingRole.type) ?: false }

}