package com.lemonfactory.crf7505.mails

import kotlinx.html.p
import kotlinx.html.stream.createHTML

class FooterTemplate {
    fun generateFooter(footer: String): String {
        return createHTML()
                .p { +footer }
    }

}
