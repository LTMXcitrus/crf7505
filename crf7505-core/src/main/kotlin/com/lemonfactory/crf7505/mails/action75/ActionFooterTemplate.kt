package com.lemonfactory.crf7505.mails.action75

import com.lemonfactory.crf7505.mails.interfaces.FooterTemplate
import kotlinx.html.p
import kotlinx.html.stream.createHTML

class ActionFooterTemplate: FooterTemplate<Nothing> {

    override fun generateFooter(footerContent: Nothing?): String {
        return createHTML()
                .p { +"Cordialement, l'équipe du V" }
    }
}
