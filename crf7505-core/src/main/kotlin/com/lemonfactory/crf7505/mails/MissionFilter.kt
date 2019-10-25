package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.*
import com.lemonfactory.crf7505.domain.model.mission.RoleType.*

class MissionFilter {

    private val defaultInterests = mapOf(
            CI to listOf(PSE2, CI),
            PSE2 to listOf(PSE1, PSE2),
            CH_VPSP to listOf(CH_VPSP, PSE2, PSE1),
            PSE1 to listOf(PSE1),
            PSC1 to listOf(PSC1)
    )

    fun filter(missions: List<Mission>, volunteer: Volunteer, localStructure: String?): List<Mission> {
        if(volunteer.interests().isEmpty()) {
            return missions.filter { mission -> mission.missionIsAMatch(defaultInterests[volunteer.role], localStructure) }
        }
        return missions.filter { mission -> mission.missionIsAMatch(volunteer.interests(), localStructure) }
    }



    private fun hasMatchingRole(missingRoles: List<Role>, interestedIn: List<RoleType>?) =
            missingRoles.any { missingRole -> interestedIn?.contains(missingRole.type) ?: false }


    private fun Mission.missionIsAMatch(interestedIn: List<RoleType>?, localStructure: String?): Boolean {
        return hasMatchingRole(this.missingRoles, interestedIn)
                || this.isOtherActivity(localStructure)

    }

    private fun Mission.isOtherActivity(localStructure: String?): Boolean {
        return this.activityGroup != ActivityGroup.US || (this.activityType == TypeActivity.REUNION && this.ul == localStructure)
    }
}