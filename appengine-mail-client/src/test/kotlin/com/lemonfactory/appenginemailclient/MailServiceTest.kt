package com.lemonfactory.appenginemailclient

import com.lemonfactory.crf7505.domain.model.CrfMail
import org.junit.jupiter.api.Test

class MailServiceTest {

    private val mailService= MailServiceImpl()


    @Test
    fun sendEmail_recapMail_shouldUseRecapInformation() {
        // Given
        val crfMail = CrfMail("sender", "recipient", "subject", "text")
        // When
        mailService.sendMails(listOf(crfMail))

        // Then
    }

}