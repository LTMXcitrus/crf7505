package com.lemonfactory.crf7505.model.mission

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    fun toDbObject(): DbMission {
        return DbMission(
                id,
                beginDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                endDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                name,
                ul,
                inscriptions.map { it.toDbObject() },
                roles.map { it.toDbObject() },
                missingRoles.map { it.toDbObject() },
                hasCommentedInscriptions,
                hasModifiedHoursInscriptions,
                activityType,
                activityGroup
        )
    }
}


data class DbMission(
        var id: String = "",
        var beginDate: String = "",
        var endDate: String = "",
        var name: String = "",
        var ul: String = "",
        var inscriptions: List<DbInscription> = mutableListOf(),
        var roles: List<DbRole> = mutableListOf(),
        var missingRoles: List<DbRole> = mutableListOf(),
        var hasCommentedInscriptions: Boolean = false,
        var hasModifiedHoursInscriptions: Boolean = false,
        var activityType: TypeActivity = TypeActivity.MISSION,
        var activityGroup: ActivityGroup? = ActivityGroup.US
) {
    fun toModel(): Mission {
        return Mission(
                id,
                LocalDateTime.parse(beginDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                name,
                ul,
                inscriptions.map { it.toModel() },
                roles.map { it.toModel() },
                missingRoles.map { it.toModel() },
                hasCommentedInscriptions,
                hasModifiedHoursInscriptions,
                activityType,
                activityGroup
        )
    }
}