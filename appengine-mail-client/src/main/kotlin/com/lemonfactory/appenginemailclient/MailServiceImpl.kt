package com.lemonfactory.appenginemailclient

import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.infrastructure.MailService
import org.slf4j.LoggerFactory
import java.io.UnsupportedEncodingException
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MailServiceImpl(private val sender: MailSender) : MailService {
    private val LOGGER = LoggerFactory.getLogger(MailServiceImpl::class.java)

    override fun sendMails(crfMails: List<CrfMail>) {
        val props = Properties()
        val session = Session.getDefaultInstance(props, null)
        try {
            crfMails.forEach { sendMail(session, it) }
        } catch (e: AddressException) {
            LOGGER.error(e.message)
        } catch (e: MessagingException) {
            LOGGER.error(e.message)
        } catch (e: UnsupportedEncodingException) {
            LOGGER.error(e.message)
        }
    }

    private fun sendMail(session: Session, crfMail: CrfMail) {
        LOGGER.info("Sending mail $crfMail")
        val msg = MimeMessage(session)
        msg.setFrom(InternetAddress(crfMail.sender))
        msg.addRecipient(Message.RecipientType.TO,
                InternetAddress(crfMail.recipient))
        msg.subject = crfMail.subject
        msg.setText(crfMail.text, "utf-8", "html")
        sender.send(msg)
    }
}
