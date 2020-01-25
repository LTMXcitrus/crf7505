package com.lemonfactory.appenginemailclient.adapter


import com.lemonfactory.crf7505.model.CrfMail
import com.sendgrid.Content
import com.sendgrid.Email
import com.sendgrid.Mail

class SendGridAdapter {

    fun transform(crfMail: CrfMail): Mail {
        val from = Email(crfMail.sender)
        val to = Email(crfMail.recipient)
        val content = Content("text/html", crfMail.text)

        return Mail(from, crfMail.subject, to, content)
    }

}
