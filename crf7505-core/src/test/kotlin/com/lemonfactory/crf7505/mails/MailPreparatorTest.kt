package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.config.ConfigKeys
import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.crf7505.utils.Missions
import com.lemonfactory.crf7505.utils.any
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.time.LocalDate

private const val SENDER = "SENDER"

class MailPreparatorTest {
    private val bodyTemplate = Mockito.mock(BodyTemplate::class.java)
    private val headerTemplate = Mockito.mock(HeaderTemplate::class.java)
    private val footerTemplate = Mockito.mock(FooterTemplate::class.java)
    private val config = Mockito.mock(Config::class.java)

    private val mailPreparator = MailPreparator(bodyTemplate, headerTemplate, footerTemplate, config)

    @Before
    fun setUp() {
        `when`(config.getEnvRequired(ConfigKeys.RECAP_SENDER)).thenReturn(SENDER)
    }

    @Test
    fun `should return header body and footer concated`() {
        // Given
        `when`(bodyTemplate.generateBody(any())).thenReturn("body")
        `when`(headerTemplate.generateHeader(any(), any(), any())).thenReturn("header")
        `when`(footerTemplate.generateFooter(any())).thenReturn("footer")
        val volunteer = Volunteer("emailAddress", "firstname", "lastname", RoleType.PSE1)

        // When
        val crfMail = mailPreparator.generateMail(volunteer, Activities(emptyList(), emptyList(), ""), "subject","header", "footer", "respMission")

        // Then
        assertThat(crfMail.recipient).isEqualTo("emailAddress")
        assertThat(crfMail.subject).isEqualTo("subject")
        assertThat(crfMail.sender).isEqualTo(SENDER)
        assertThat(crfMail.text).isEqualTo("header\nbodyfooter")
    }


    @Test
    fun `should return header body-with-missions and footer concated`() {
        // Given
        `when`(bodyTemplate.generateBody(any())).thenCallRealMethod()
        `when`(headerTemplate.generateHeader(any(), any(), any())).thenReturn("header")
        `when`(footerTemplate.generateFooter(any())).thenReturn("footer")
        val volunteer = Volunteer("emailAddress", "firstname", "lastname", RoleType.PSE1)
        val now = LocalDate.of(2019, 10, 15)
        val mission = Missions.aMissionWithMissingRoles(now)

        // When
        val crfMail = mailPreparator.generateMail(volunteer, Activities(listOf(mission), listOf(), ""), "subject", "header", "footer", "respMission")

        // Then
        assertThat(crfMail.recipient).isEqualTo("emailAddress")
        assertThat(crfMail.subject).isEqualTo("subject")
        assertThat(crfMail.sender).isEqualTo(SENDER)
        assertThat(crfMail.text).isEqualToIgnoringWhitespace(
                """header
<div>
  <h3>Les missions locales</h3>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/" style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span> - Il manque: 1 PSE1</span></li>
  </ul>
  <h3>Les missions extérieures</h3>
<span>Pas de missions extérieures</span></div>
footer"""
        )
    }

}
