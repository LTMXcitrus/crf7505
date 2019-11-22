package com.lemonfactory.crf7505.mails.interfaces

import com.lemonfactory.crf7505.domain.model.CrfMail

interface MailPreparator<T> {

    fun generateMail(mailParameters: T): CrfMail

}
