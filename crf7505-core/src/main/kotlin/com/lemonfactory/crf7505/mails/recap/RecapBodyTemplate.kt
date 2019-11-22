package com.lemonfactory.crf7505.mails.recap

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.domain.model.mission.Role
import com.lemonfactory.crf7505.mails.interfaces.BodyTemplate
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

private const val DATE_FORMAT = "EEEE dd MMMM"
private const val DATE_TIME_FORMAT = "HH'h'mm"

class RecapBodyTemplate: BodyTemplate<Activities> {

    override fun generateBody(bodyContent: Activities): String {
        return createHTML().div {
            h3 { +"Les missions locales" }
            displayAllMissions(bodyContent.localActivities, "Pas de missions locales", bodyContent.localStructure)

            h3 { +"Les missions extérieures" }
            if (bodyContent.externalActivities.isNotEmpty()) {
                p {
                    style = "color: red;text-decoration: underline"
                    +"Comme d'habitude, merci de prévenir le responsable missions avant toute inscription sur Pegass."
                }
            }
            displayAllMissions(bodyContent.externalActivities, "Pas de missions extérieures")
        }
    }

    private fun DIV.displayAllMissions(missions: List<Mission>, emptyPhrase: String, structure: String? = null) {
        if (missions.isEmpty()) {
            span { +emptyPhrase }
        } else {
            displayMissions(missionsByDay(missions, structure))
            val otherActivities = otherActivitiesByDay(missions, structure)
            if (otherActivities.isNotEmpty()) {
                br { }
                span {
                    style = "color: grey;"
                    +"Les autres missions"
                }
                br { }
                br { }
                displayMissions(otherActivities)
            }
        }
    }

    private fun DIV.displayMissions(missions: Map<LocalDate, List<Mission>>) {
        missions.entries.map { (day, missions) ->
            span {
                style = "color: grey;"
                +day.format(DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.FRANCE)).capitalize()
            }
            ul {
                missions.map {
                    li {
                        missionTitle(it)
                        span {
                            +missionRolesString(it)
                        }
                    }
                }
            }
        }
    }

    private fun missionHours(mission: Mission): String {
        val begin = mission.beginDate.format(DateTimeFormatter.ofPattern("HH'h'mm"))
        val end = mission.endDate.format(DateTimeFormatter.ofPattern("HH'h'mm"))
        return "$begin-$end"
    }

    private fun LI.missionTitle(mission: Mission) {
        +"${missionHours(mission)}: "
        a(href = "https://pegass.croix-rouge.fr/planning-des-activites/activite/${mission.id}/") {
            style = "text-decoration: underline;font-weight: bold;"
            b { +"${mission.name} (${mission.ul})" }
        }
    }

    private fun missionsByDay(missions: List<Mission>, structure: String?) =
            missions.filter { structure == null || !it.isOtherActivity(structure) }
                    .groupBy { it.date }

    private fun otherActivitiesByDay(missions: List<Mission>, structure: String?) =
            missions.filter { structure != null && it.isOtherActivity(structure) }
                    .groupBy { it.date }

    private fun missionRolesString(mission: Mission): String {
        if (mission.missingRoles.isEmpty()) {
            return ""
        }
        val partialMissingRolesString = partialMissingRolesString(mission, mission.missingRoles)
        val completeMissingRolesString = completeMissingRolesString(mission.missingRoles)

        val missingRolesString = partialMissingRolesString + completeMissingRolesString

        return missingRolesString.joinToString(", ", " - Il manque: ")
    }

    private fun completeMissingRolesString(missingRoles: List<Role>): List<String> {
        val completeMissingRoles = missingRoles.filter { role -> role.beginDate == null && role.endDate == null }
        return completeMissingRoles.map { role -> "${role.quantity} ${role.type.toString}" }
    }

    private fun partialMissingRolesString(mission: Mission, missingRoles: List<Role>): List<String> {
        val partialMissingRoles = missingRoles.filter { role -> role.beginDate != null || role.endDate != null }
        return partialMissingRoles.map { partialMissingRoleString(mission, it) }
                .filter { it.isNotBlank() }
    }

    private fun partialMissingRoleString(mission: Mission, partialMissingRole: Role): String {
        val begin = computeTime(partialMissingRole.beginDate, mission.beginDate)
        val end = computeTime(partialMissingRole.endDate, mission.endDate)
        return "${partialMissingRole.quantity} ${partialMissingRole.type.toString} ($begin-$end)"
    }

    private fun computeTime(custom: LocalTime?, actual: LocalDateTime): String {
        if (custom == null) {
            return actual.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
        }
        return custom.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
    }

}
