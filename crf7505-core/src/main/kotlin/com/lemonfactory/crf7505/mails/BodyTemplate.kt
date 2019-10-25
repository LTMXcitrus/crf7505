package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.mission.Mission
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

private const val DATE_FORMAT = "EEEE dd MMMM"

class BodyTemplate {

    fun generateBody(activities: Activities): String {
        return createHTML().div {
            h3 { +"Les missions locales" }
            displayAllMissions(activities.localActivities, "Pas de missions locales", activities.localStructure)

            h3 { +"Les missions extérieures" }
            displayAllMissions(activities.externalActivities, "Pas de missions extérieures")
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
                span { +"Les autres missions" }
                br {  }
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
                        b { +it.name }
                        span {
                            + " - ${missionRolesString(it)}"
                        }
                    }
                }
            }
        }
    }

    private fun missionsByDay(missions: List<Mission>, structure: String?) =
            missions.filter { structure == null || !it.isOtherActivity(structure) }
                    .groupBy { it.date }

    private fun otherActivitiesByDay(missions: List<Mission>, structure: String?) =
            missions.filter { structure != null && it.isOtherActivity(structure) }
                    .groupBy { it.date }

    private fun missionRolesString(mission: Mission): String {
        return mission.missingRoles.joinToString(", ", "Il manque: ") { "${it.quantity} ${it.type.toString}" }
    }

}
