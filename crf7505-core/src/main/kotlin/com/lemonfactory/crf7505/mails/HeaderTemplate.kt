package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import kotlinx.html.*
import kotlinx.html.stream.createHTML

class HeaderTemplate {
    fun generateHeader(volunteer: Volunteer, header: String): String {
        return createHTML().div {
            p { +"Bonjour ${volunteer.firstname}," }
            p { +header }
            p { +"Tu trouveras ci-après le récapitulatif de la semaine." }
            p {
                +"Le responsable missions de la semaine est "
                b {
                    +"Emmanuel"
                }
            }
        }
    }

}
