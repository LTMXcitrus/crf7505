package com.lemonfactory.appenginemailclient

import com.lemonfactory.crf7505.domain.model.CrfMail
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.mockito.Mockito
import javax.mail.internet.MimeMessage

class MailServiceTest {

    private val sender = Mockito.mock(MailSender::class.java)
    private val mailService= MailServiceImpl(sender)

    @Test
    fun sendMail_oneMail_should_call_sender() {
        // Given
        val crfMail = CrfMail("sender", "recipient", "subject", "text")

        // when
        mailService.sendMails(listOf(crfMail, crfMail))

        // Then
        verify(sender, times(2)).send(any())
    }

}
