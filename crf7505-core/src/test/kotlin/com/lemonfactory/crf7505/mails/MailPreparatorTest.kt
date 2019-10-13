package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.crf7505.utils.Missions
import com.lemonfactory.crf7505.utils.any
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter
import java.util.*

class MailPreparatorTest {
    private val SENDER = "SENDER"
    val bodyTemplate = Mockito.mock(BodyTemplate::class.java)
    val headerTemplate = Mockito.mock(HeaderTemplate::class.java)
    val footerTemplate = Mockito.mock(FooterTemplate::class.java)

    val mailPreparator = MailPreparator(bodyTemplate, headerTemplate, footerTemplate, SENDER)

    @Test
    fun `should return header body and footer concated`() {
        // Given
        `when`(bodyTemplate.generateBody(anyList())).thenReturn("body")
        `when`(headerTemplate.generateHeader(any())).thenReturn("header")
        `when`(footerTemplate.generateFooter()).thenReturn("footer")
        val volunteer = Volunteer("emailAddress", "firstname", "lastname", RoleType.PSE1)

        // When
        val crfMail = mailPreparator.generateMail(volunteer, emptyList())

        // Then
        assertThat(crfMail.recipient).isEqualTo("emailAddress")
        assertThat(crfMail.subject).isEqualTo("subject")
        assertThat(crfMail.sender).isEqualTo(SENDER)
        assertThat(crfMail.text).isEqualTo("header\nbodyfooter")
    }


    @Test
    fun `should return header body-with-missions and footer concated`() {
        // Given
        `when`(bodyTemplate.generateBody(anyList())).thenCallRealMethod()
        `when`(headerTemplate.generateHeader(any())).thenReturn("header")
        `when`(footerTemplate.generateFooter()).thenReturn("footer")
        val volunteer = Volunteer("emailAddress", "firstname", "lastname", RoleType.PSE1)
        val now = now()
        val mission = MissionsDay(now, listOf(Missions.aMissionWithMissingRoles()))

        // When
        val crfMail = mailPreparator.generateMail(volunteer, listOf(mission))

        // Then
        assertThat(crfMail.recipient).isEqualTo("emailAddress")
        assertThat(crfMail.subject).isEqualTo("subject")
        assertThat(crfMail.sender).isEqualTo(SENDER)
        assertThat(crfMail.text).isEqualToIgnoringWhitespace(
                """header
                    <div><b>${now.format(DateTimeFormatter.ofPattern("EEEE dd MMMM")).capitalize()}</b>
                        <ul>
                            <li>name</li>
                        </ul>
                    </div>
                footer"""
        )
    }

}