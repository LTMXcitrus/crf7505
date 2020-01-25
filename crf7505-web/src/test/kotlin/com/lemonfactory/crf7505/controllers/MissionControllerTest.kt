package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.api.GenerateMailBody
import com.lemonfactory.crf7505.model.Activities
import com.lemonfactory.crf7505.model.CrfMail
import com.lemonfactory.crf7505.model.PegassUser
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

    private val missionRepository: MissionRepository = mock()
    private val mailService: MailService = mock()
    private val mailHandler: MailHandler = mock()

    private val missionController = MissionController(missionRepository, mailService, mailHandler)

    @Test
    fun `activities should`() {
        // Given
        val pegassUser = PegassUser("user", "pwd")
        val start = LocalDateTime.now()
        val end = LocalDateTime.now().plusHours(1)
        val addedDaysForLocalMissions = 7
        `when`(missionRepository.getActivities(pegassUser, start, end, addedDaysForLocalMissions)).thenReturn(Activities(emptyList(), emptyList(), "userStructure"))

        // When
        val responseBody = missionController.activities(pegassUser, start, end, addedDaysForLocalMissions)

        // Then
        verify(missionRepository).getActivities(pegassUser, start, end, addedDaysForLocalMissions)
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
                activities,
                "respMission"
        )
        val mail = CrfMail("sender", "recipient", "subject", "text")
        `when`(mailHandler.genMails("subject", "header", activities, "footer", "respMission")).thenReturn(listOf(mail))

        // When
        val responseBody = missionController.recapMissions(body)

        // Then
        verify(mailHandler).genMails("subject", "header", activities, "footer", "respMission")
        assertThat(responseBody).isEqualTo("[{\"sender\":\"sender\",\"recipient\":\"recipient\",\"subject\":\"subject\",\"text\":\"text\"}]")
    }

    @Test
    fun `sendRecap should`() {
        // Given
        val mail = CrfMail("sender", "recipient", "subject", "text")

        // When
        missionController.sendRecap(listOf(mail))

        // Then
        verify(mailService).sendMails(listOf(mail))
    }


}
