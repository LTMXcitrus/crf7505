package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.model.Volunteer
import kotlinx.html.*
import kotlinx.html.stream.createHTML

class HeaderTemplate {
    fun generateHeader(volunteer: Volunteer, header: String, respMission: String): String {
        return createHTML().div {
            p { +"Bonjour ${volunteer.firstname}," }
            p {
                +"Tu trouveras ci-après le récapitulatif de la semaine."
                br { }
                +"Le responsable missions est "
                b {
                    +respMission
                }
            }
            p { +header }
        }
    }

}
