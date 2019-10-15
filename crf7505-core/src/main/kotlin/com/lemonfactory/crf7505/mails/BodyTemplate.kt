package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import java.time.format.DateTimeFormatter
import java.util.*

private const val DATE_FORMAT = "EEEE dd MMMM"

class BodyTemplate {

    fun generateBody(missions: List<MissionsDay>): String {
        return createHTML().div {
            missions.map { day ->
                b {
                    +day.date.format(DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.FRANCE)).capitalize()
                }
                ul {
                    day.missions.map {
                        li {
                             + "${it.name} "
                            span {
                                style = "background-color: #EEEEEE; padding: 5px"
                                +missionRolesString(it)
                            }
                        }
                    }
                }

            }
        }
    }

    private fun missionRolesString(mission: Mission): String {
        return mission.missingRoles.joinToString (", ", "Il manque: ") {"${it.quantity} ${it.type.toString}"}
    }

}
