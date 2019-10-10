package com.lemonfactory.crf7505.utils

import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.domain.model.mission.Role
import com.lemonfactory.crf7505.domain.model.mission.RoleType
import java.time.LocalDateTime
import java.time.LocalDateTime.now

object Missions {

    fun aMissionWithMissingRoles(): Mission {
        //return Mission(
        //        "id",
        //        now(),
        //        now().plusHours(2),
        //        "name",
        //        "ul",
        //        emptyList(),
        //        TODO()
        //)
        TODO()
    }

    fun aMissionWithMissingRoles(beginDate: LocalDateTime, endDate: LocalDateTime): Mission =
            Mission(
                    "code",
                    beginDate,
                    endDate,
                    "name",
                    "ul",
                    emptyList(),
                    emptyList(),
                    listOf(Role(RoleType.PSC1,1))
            )

    fun aMissionWithoutMissingRoles(beginDate: LocalDateTime, endDate: LocalDateTime): Mission =
            Mission(
                    "code",
                    beginDate,
                    endDate,
                    "name",
                    "ul",
                    emptyList(),
                    emptyList(),
                    emptyList()
            )

    fun aMissionsWithMissingRolesFor(roles: List<RoleType>) =
            Mission(
                    "code",
                    now(),
                    now().plusHours(2),
                    "name",
                    "ul",
                    emptyList(),
                    emptyList(),
                    roles.map { Role(it, 1) }
            )

}