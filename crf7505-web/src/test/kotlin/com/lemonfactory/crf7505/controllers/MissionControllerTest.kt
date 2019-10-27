package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.api.GenerateMailBody
import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.MailService
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.mails.MailHandler
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import java.time.LocalDateTime


class MissionControllerTest {

    val missionRepository: MissionRepository = mock()
    val mailService: MailService = mock()
    val mailHandler: MailHandler = mock()

    val missionController = MissionController(missionRepository, mailService, mailHandler)

    @Test
    fun `activities should`() {
        // Given
        val pegassUser = PegassUser("user", "pwd")
        val start = LocalDateTime.now()
        val end = LocalDateTime.now().plusHours(1)
        `when`(missionRepository.getActivities(pegassUser, start, end)).thenReturn(Activities(emptyList(), emptyList(), "userStructure"))

        // When
        val responseBody = missionController.activities(pegassUser, start, end)

        // Then
        verify(missionRepository).getActivities(pegassUser, start, end)
        assertThat(responseBody).isEqualTo("{\"localActivities\":[],\"externalActivities\":[],\"localStructure\":\"userStructure\"}")

    }

    @Test
    fun `recapMissions should`() {
        // Given
        val activities = Activities(emptyList(), emptyList(), "localStructure")
        val body = GenerateMailBody(
                "subject",
                "header",
                "footer",
                activities
        )
        val mail = CrfMail("sender", "recipient", "subject", "text")
        `when`(mailHandler.genMails("subject", "header", activities, "footer")).thenReturn(listOf(mail))

        // When
        val responseBody = missionController.recapMissions(body)

        // Then
        verify(mailHandler).genMails("subject", "header", activities, "footer")
        assertThat(responseBody).isEqualTo("[{\"sender\":\"sender\",\"recipient\":\"recipient\",\"subject\":\"subject\",\"text\":\"text\"}]")
    }

    @Test
    fun `sendRecap should`() {
        // Given
        val mail = CrfMail("sender", "recipient", "subject", "text")

        // When
        val responseBody = missionController.sendRecap(listOf(mail))

        // Then
        verify(mailService).sendMails(listOf(mail))
        assertThat(responseBody).isEqualTo("true")
    }


}