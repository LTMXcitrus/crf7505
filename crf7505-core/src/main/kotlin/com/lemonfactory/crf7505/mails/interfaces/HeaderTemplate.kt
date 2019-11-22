package com.lemonfactory.crf7505.mails.interfaces

interface HeaderTemplate<T> {

    fun generateHeader(headerContent: T? = null): String

}
