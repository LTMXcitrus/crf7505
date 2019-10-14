package com.lemonfactory.appenginemailclient

import javax.mail.Transport
import javax.mail.internet.MimeMessage

class MailSender {

    fun send(msg: MimeMessage) {
        Transport.send(msg)
    }

}
