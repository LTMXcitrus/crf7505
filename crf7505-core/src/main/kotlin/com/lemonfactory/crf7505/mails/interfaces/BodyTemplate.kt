package com.lemonfactory.crf7505.mails.interfaces

interface BodyTemplate<T> {

    fun generateBody(bodyContent: T): String

}
