package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.model.CrfMail

interface MailService {

    fun sendMails(crfMails: List<CrfMail>): List<String>

}
