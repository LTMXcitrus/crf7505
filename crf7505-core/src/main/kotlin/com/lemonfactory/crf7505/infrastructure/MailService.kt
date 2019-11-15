package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.CrfMail

interface MailService {

    fun sendMails(crfMails: List<CrfMail>): List<String>

}
