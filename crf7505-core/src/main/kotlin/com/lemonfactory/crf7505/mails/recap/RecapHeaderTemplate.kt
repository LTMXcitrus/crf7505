package com.lemonfactory.crf7505.mails.recap

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.mails.interfaces.HeaderTemplate
import kotlinx.html.*
import kotlinx.html.stream.createHTML

data class RecapHeader(val volunteer: Volunteer,
                       val header: String,
                       val respMission: String)

class RecapHeaderTemplate : HeaderTemplate<RecapHeader> {

    override fun generateHeader(headerContent: RecapHeader?): String {
        return with(headerContent!!) {
            createHTML().div {
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

}
