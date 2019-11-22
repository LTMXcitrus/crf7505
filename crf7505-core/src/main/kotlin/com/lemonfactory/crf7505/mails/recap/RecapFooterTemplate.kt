package com.lemonfactory.crf7505.mails.recap

import com.lemonfactory.crf7505.mails.interfaces.FooterTemplate
import kotlinx.html.p
import kotlinx.html.stream.createHTML

class RecapFooterTemplate: FooterTemplate<String> {

    override fun generateFooter(footerContent: String?): String {
        return createHTML()
                .p { +footerContent!! }
    }

}
