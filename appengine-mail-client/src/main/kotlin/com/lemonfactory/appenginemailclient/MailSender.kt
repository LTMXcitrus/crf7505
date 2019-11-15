package com.lemonfactory.appenginemailclient

import com.lemonfactory.crf7505.config.Config
import com.sendgrid.Mail
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import org.slf4j.LoggerFactory
import java.io.IOException

class MailSender(private val config: Config) {
    private val LOGGER = LoggerFactory.getLogger(MailSender::class.java)

    fun sendMails(mails: List<Mail>): List<String> {
        val sendGridApiKey = config.getEnvRequired("SEND_GRID_API_KEY")
        return mails.map { sendMail(it, sendGridApiKey) }
    }

    private fun sendMail(mail: Mail, sendGridApiKey: String): String {
        val sendGrid = SendGrid(sendGridApiKey)
        val request = Request()
        try {
            request.method = Method.POST
            request.endpoint = "mail/send"
            request.body = mail.build()
            val response = sendGrid.api(request)
            return response.body
        } catch (e: IOException) {
            LOGGER.error(e.message, e)
        }
        return ""
    }

}
