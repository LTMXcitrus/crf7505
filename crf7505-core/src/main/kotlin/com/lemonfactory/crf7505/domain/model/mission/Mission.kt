package com.lemonfactory.crf7505.domain.model.mission

import java.time.LocalDate
import java.time.LocalDateTime

data class Mission(val id: String,
                   val beginDate: LocalDateTime,
                   val endDate: LocalDateTime,
                   val name: String,
                   val ul: String,
                   val inscriptions: List<Inscription>,
                   val roles: List<Role>,
                   val missingRoles: List<Role>,
                   val hasCommentedInscriptions: Boolean = false,
                   val hasModifiedHoursInscriptions: Boolean = false,
                   val activityType: TypeActivity = TypeActivity.MISSION,
                   val activityGroup: ActivityGroup? = ActivityGroup.US,
                   val date: LocalDate = beginDate.toLocalDate()
) {

    fun missionIsAMatch(interestedIn: List<RoleType>?, localStructure: String?): Boolean {
        return hasMatchingRole(this.missingRoles, interestedIn)
                || this.isOtherActivity(localStructure)
                || this.isACompleteLocalMission(localStructure)

    }

    private fun isACompleteLocalMission(localStructure: String?): Boolean {
        return (localStructure?.equals(this.ul) ?: false) && missingRoles.isEmpty()
    }

    fun isOtherActivity(localStructure: String?): Boolean {
        return this.activityGroup != ActivityGroup.US || (this.activityType == TypeActivity.REUNION && this.ul == localStructure)
    }

    private fun hasMatchingRole(missingRoles: List<Role>, interestedIn: List<RoleType>?) =
            missingRoles.any { missingRole -> interestedIn?.contains(missingRole.type) ?: false }
}
