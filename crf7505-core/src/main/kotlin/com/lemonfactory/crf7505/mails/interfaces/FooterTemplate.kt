package com.lemonfactory.crf7505.mails.interfaces

interface FooterTemplate<T> {

    fun generateFooter(footerContent: T? = null): String

}
