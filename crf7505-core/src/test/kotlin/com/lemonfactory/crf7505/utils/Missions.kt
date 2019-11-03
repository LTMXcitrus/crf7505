package com.lemonfactory.crf7505.utils

import com.lemonfactory.crf7505.domain.model.mission.*
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.LocalTime

object Missions {

    fun aMission(now: LocalDate = now()): Mission {
        return Mission(
                "id",
                now.atStartOfDay(),
                now.atStartOfDay().plusHours(2),
                "name",
                "ul",
                emptyList(),
                emptyList(),
                listOf(Role(RoleType.PSE1, 1))
        )
    }

    fun aMissionWithMissingRoles(now: LocalDate = now()): Mission {
        return Mission(
                "id",
                now.atStartOfDay(),
                now.atStartOfDay().plusHours(2),
                "name",
                "ul",
                emptyList(),
                emptyList(),
                listOf(Role(RoleType.PSE1, 1))

        )
    }

    fun aLocalReunion(now: LocalDate = now()): Mission =
            Mission(
                    "id",
                    now.atStartOfDay(),
                    now.atStartOfDay().plusHours(2),
                    "Réunion de l'US",
                    "ul",
                    emptyList(),
                    emptyList(),
                    listOf(Role(RoleType.PARTICIPANT, 15)),
                    activityType = TypeActivity.REUNION,
                    activityGroup = ActivityGroup.US
            )

    fun aMissionWithMissingRoles(beginDate: LocalDateTime, endDate: LocalDateTime): Mission =
            Mission(
                    "codes",
                    beginDate,
                    endDate,
                    "name",
                    "ul",
                    emptyList(),
                    emptyList(),
                    listOf(Role(RoleType.PSC1, 1))
            )

    fun aMissionWithoutMissingRoles(beginDate: LocalDateTime, endDate: LocalDateTime): Mission =
            Mission(
                    "codes",
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
                    "codes",
                    now().atStartOfDay(),
                    now().atStartOfDay().plusHours(2),
                    "name",
                    "ul",
                    emptyList(),
                    emptyList(),
                    roles.map { Role(it, 1) }
            )

    fun aMissionWithPartialMissingRoles(now: LocalDate) = Mission(
            "id",
            now.atStartOfDay(),
            now.atStartOfDay().plusHours(2),
            "name",
            "ul",
            emptyList(),
            emptyList(),
            listOf(Role(RoleType.PSE1, 1, LocalTime.of(9, 0), LocalTime.of(13, 0)),
                    Role(RoleType.PSE1, 1))
    )
}
