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
}
