package com.lemonfactory.crf7505.domain.model.mission

import java.time.LocalDate

data class MissionsDay(val date: LocalDate,
                       val missions: List<Mission>) {

    fun withPositionForRole(role: RoleType): MissionsDay? {
        val missionsWithPositionForRole = missions.filter { it.hasPositionForRole(role) }
        if(missionsWithPositionForRole.isEmpty()) {
            return null
        }
        return MissionsDay(date, missionsWithPositionForRole)
    }
}