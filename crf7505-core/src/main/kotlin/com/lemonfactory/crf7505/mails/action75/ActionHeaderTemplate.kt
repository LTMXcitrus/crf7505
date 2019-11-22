package com.lemonfactory.crf7505.mails.action75

import com.lemonfactory.crf7505.mails.interfaces.HeaderTemplate
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.p
import kotlinx.html.stream.createHTML

class ActionHeaderTemplate: HeaderTemplate<Nothing?> {

    override fun generateHeader(headerContent: Nothing?): String {
        return createHTML().div {
            p { +"Bonjour," }
            p {
                +"Ci-joint un récapitulatif des postes de la semaine."
                br { }
            }
        }
    }
}
