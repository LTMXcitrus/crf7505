package com.lemonfactory.appenginemailclient

import com.lemonfactory.appenginemailclient.adapter.SendGridAdapter
import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.infrastructure.MailService

class MailServiceImpl(
        private val sender: MailSender,
        private val adapter: SendGridAdapter
) : MailService {

    override fun sendMails(crfMails: List<CrfMail>): List<String> {
        return sender.sendMails(crfMails.map { adapter.transform(it) })
    }
}
