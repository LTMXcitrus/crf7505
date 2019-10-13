package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import kotlinx.html.b
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.span
import kotlinx.html.stream.createHTML

class HeaderTemplate {
    fun generateHeader(volunteer: Volunteer): String {
        return createHTML().div {
            span { +"Bonjour ${volunteer.firstname}," }
            br { }
            span { +"Tu trouveras ci-après le récapitulatif de la semaine." }
            br {}
            span { +"Le responsable missions de la semaine est "
                b{
                    +"Emmanuel"
                }
            }
            br {  }
        }
    }

}
