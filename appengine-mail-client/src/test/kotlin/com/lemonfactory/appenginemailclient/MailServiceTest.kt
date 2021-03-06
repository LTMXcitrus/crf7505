package com.lemonfactory.appenginemailclient

import com.lemonfactory.appenginemailclient.adapter.SendGridAdapter
import com.lemonfactory.crf7505.domain.model.CrfMail
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.mockito.Mockito

class MailServiceTest {

    private val sender = Mockito.mock(MailSender::class.java)
    private val adapter = Mockito.mock(SendGridAdapter::class.java)
    private val mailService= MailServiceImpl(sender, adapter)

    @Test
    fun sendMail_oneMail_should_call_sender() {
        // Given
        val crfMail = CrfMail("sender", "recipient", "subject", "text")

        // when
        mailService.sendMails(listOf(crfMail, crfMail))

        // Then
        verify(sender).sendMails(any())
    }

}
