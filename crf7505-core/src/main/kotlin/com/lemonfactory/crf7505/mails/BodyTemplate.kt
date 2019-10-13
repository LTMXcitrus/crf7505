package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import kotlinx.html.b
import kotlinx.html.div
import kotlinx.html.li
import kotlinx.html.stream.createHTML
import kotlinx.html.ul
import java.time.format.DateTimeFormatter

private const val DATE_FORMAT = "EEEE dd MMMM"

class BodyTemplate {

    fun generateBody(missions: List<MissionsDay>): String {
        return createHTML().div {
            missions.map { day ->
                b {
                    +day.date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)).capitalize()
                }
                ul {
                    day.missions.map {
                        li {
                            +it.name
                        }
                    }
                }

            }
        }
    }

}