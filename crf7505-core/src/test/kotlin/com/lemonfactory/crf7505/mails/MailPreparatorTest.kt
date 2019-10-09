package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.RoleType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class MailPreparatorTest {
    private val SENDER = "SENDER"
    val bodyTemplate = Mockito.mock(BodyTemplate::class.java)
    val headerTemplate = Mockito.mock(HeaderTemplate::class.java)
    val footerTemplate = Mockito.mock(FooterTemplate::class.java)

    val mailPreparator = MailPreparator(bodyTemplate, headerTemplate, footerTemplate, SENDER)

    @Test
    fun `should return header body and footer concated`() {
        // Given
        `when`(bodyTemplate.generateBody()).thenReturn("body")
        `when`(headerTemplate.generateHeader()).thenReturn("header")
        `when`(footerTemplate.generateFooter()).thenReturn("footer")
        val volunteer = Volunteer("firstname", "lastname", RoleType.PSE1, "emailAddress")

        // When
        val crfMail = mailPreparator.generateMails(volunteer, emptyList())

        // Then
        assertThat(crfMail.recipient).isEqualTo("emailAddress")
        assertThat(crfMail.subject).isEqualTo("subject")
        assertThat(crfMail.sender).isEqualTo(SENDER)
        assertThat(crfMail.text).isEqualTo("headerbodyfooter")
    }

}