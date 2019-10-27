package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.infrastructure.VolunteerRepository
import com.lemonfactory.crf7505.utils.Missions
import com.lemonfactory.crf7505.utils.any
import com.lemonfactory.crf7505.utils.argumentCaptorTriple
import com.lemonfactory.crf7505.utils.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`

class MailHandlerTest {

    private val mailPreparator: MailPreparator = mock()
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
        mailHandler.genMails("subject", "header", activities, "footer")

        // Then
        argumentCaptorTriple<Volunteer, List<Mission>, String>().let { (volunteer, list, string) ->
            verify(mailPreparator).generateMail(volunteer.capture(), list.capture(), list.capture(), string.capture(), string.capture(), string.capture())
            assertEquals(Volunteer(), volunteer.firstValue)
            assertThat(list.allValues).containsOnly(emptyList())
            assertThat(string.allValues).containsExactly("subject", "header", "footer")
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
        mailHandler.genMails("subject", "header", activities, "footer")

        // Then

        verify(mailPreparator, times(2)).generateMail(any(), any(), any(), any(), any(), any())

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
        mailHandler.genMails("subject", "header", activities, "footer")

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
        mailHandler.genMails("subject", "header", activities, "footer")

        // Then
        verify(missionFilter, times(4)).filter(any(), any(), any())
    }
}