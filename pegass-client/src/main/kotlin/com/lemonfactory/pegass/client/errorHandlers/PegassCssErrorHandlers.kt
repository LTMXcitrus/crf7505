package com.lemonfactory.pegass.client.errorHandlers

import com.gargoylesoftware.css.parser.CSSErrorHandler
import com.gargoylesoftware.css.parser.CSSParseException

class PegassCssErrorHandlers : CSSErrorHandler {

    override fun warning(p0: CSSParseException) {
    }

    override fun error(p0: CSSParseException) {
    }

    override fun fatalError(p0: CSSParseException) {
    }
}
