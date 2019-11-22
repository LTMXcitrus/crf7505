package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.VolunteerRepository
import com.lemonfactory.crf7505.mails.recap.RecapMailParameters
import com.lemonfactory.crf7505.mails.recap.RecapMailPreparator
import com.lemonfactory.crf7505.utils.Missions
import com.lemonfactory.crf7505.utils.any
import com.lemonfactory.crf7505.utils.mock
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`

class MailHandlerTest {

    private val mailPreparator: RecapMailPreparator = mock()
    private val volunteerRepository: VolunteerRepository = mock()
    private val missionFilter: MissionFilter = mock()

    private val mailHandler = MailHandler(mailPreparator, volunteerRepository, missionFilter)

    @Test
    fun `should call missionFilter and use its result`() {
        // Given
        val activities = Activities(
                listOf(Missions.aMissionWithMissingRoles()),
                listOf(Missions.aMissionWithMissingRoles()),
                "V"
        )
        `when`(volunteerRepository.retrieveAllVolunteers()).thenReturn(listOf(Volunteer()))
        `when`(missionFilter.filter(any(), any(), any())).thenReturn(emptyList())

        // When
        mailHandler.genMails("subject", "header", activities, "footer", "respMission")

        // Then
        argumentCaptor<RecapMailParameters>().apply {
            verify(mailPreparator).generateMail(capture())
            assertThat(Volunteer()).isEqualTo(firstValue.volunteer)
            assertThat(firstValue.activities).isEqualTo(Activities(emptyList(), emptyList(), "V"))
            assertThat(firstValue.subject).isEqualTo("subject")
            assertThat(firstValue.header).isEqualTo("header")
            assertThat(firstValue.footer).isEqualTo("footer")
            assertThat(firstValue.respMission).isEqualTo("respMission")
        }

    }

    @Test
    fun `should call mailPreparator for each volunteer`() {
        // Given
        val activities = Activities(
                listOf(Missions.aMissionWithMissingRoles()),
                listOf(Missions.aMissionWithMissingRoles()),
                "V"
        )
        `when`(volunteerRepository.retrieveAllVolunteers()).thenReturn(listOf(Volunteer(), Volunteer()))
        `when`(missionFilter.filter(any(), any(), any())).thenReturn(emptyList())

        // When
        mailHandler.genMails("subject", "header", activities, "footer", "respMission")

        // Then

        verify(mailPreparator, times(2)).generateMail(any())

    }

    @Test
    fun `should call volunteerRepository once`() {
        // Given
        val activities = Activities(
                listOf(Missions.aMissionWithMissingRoles()),
                listOf(Missions.aMissionWithMissingRoles()),
                "V"
        )
        `when`(volunteerRepository.retrieveAllVolunteers()).thenReturn(listOf(Volunteer(), Volunteer()))
        `when`(missionFilter.filter(any(), any(), any())).thenReturn(emptyList())

        // When
        mailHandler.genMails("subject", "header", activities, "footer", "respMission")

        // Then
        verify(volunteerRepository).retrieveAllVolunteers()
    }

    @Test
    fun `should call missionFilter twice for each volunteer`() {
        // Given
        val activities = Activities(
                listOf(Missions.aMissionWithMissingRoles()),
                listOf(Missions.aMissionWithMissingRoles()),
                "V"
        )
        `when`(volunteerRepository.retrieveAllVolunteers()).thenReturn(listOf(Volunteer(), Volunteer()))
        `when`(missionFilter.filter(any(), any(), any())).thenReturn(emptyList())

        // When
        mailHandler.genMails("subject", "header", activities, "footer", "respMission")

        // Then
        verify(missionFilter, times(4)).filter(any(), any(), any())
    }
}
