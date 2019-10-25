package com.lemonfactory.crf7505.repository

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.ConnectedUserResolver
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.crf7505.utils.Missions.aMissionWithMissingRoles
import com.lemonfactory.crf7505.utils.Missions.aMissionWithoutMissingRoles
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.time.LocalDateTime

class MissionRepositoryImplTest {

    private val midday = LocalDateTime.now().withHour(12)
    private val aPegassUser = PegassUser("", "")

    private val missionService = Mockito.mock(MissionService::class.java)
    private val connectedUserResolver = Mockito.mock(ConnectedUserResolver::class.java)

    private val missionRepository = MissionRepositoryImpl(missionService, connectedUserResolver)

    @Test
    fun `getMissionsByDay remove out of date range missions`() {
        // Given

        val beginLimit = midday.minusHours(2)
        val endLimit = midday.plusHours(2)

        val missionCompletelyBefore = aMissionWithMissingRoles(midday.minusDays(2), midday.minusDays(1))
        val missionPartiallyBefore = aMissionWithMissingRoles(midday.minusHours(3), midday)
        val missionBetween = aMissionWithMissingRoles(midday.minusHours(1), midday.plusHours(1))
        val missionPartiallyAfter = aMissionWithMissingRoles(midday.plusHours(1), midday.plusHours(3))
        val missionCompletelyAfter = aMissionWithMissingRoles(midday.plusHours(3), midday.plusHours(4))
        val missionCovering = aMissionWithMissingRoles(midday.minusHours(3), midday.plusHours(3))

        `when`(missionService.getAllMissions(aPegassUser, beginLimit, endLimit)).thenReturn(listOf(
                missionCompletelyBefore,
                missionPartiallyBefore,
                missionBetween,
                missionPartiallyAfter,
                missionCompletelyAfter,
                missionCovering
        ))

        // When
        val missionsOtherStructures = missionRepository.getActivities(PegassUser("", ""), beginLimit, endLimit).externalActivities

        // Then
        assertThat(missionsOtherStructures).containsExactlyInAnyOrder(missionBetween, missionPartiallyAfter)
    }

    @Test
    fun `getMissionsByDay remove complete missions but keep mission with commented or modified inscriptions`() {
        // Given

        val beginLimit = midday.minusHours(2)
        val endLimit = midday.plusHours(2)

        val missionComplete = aMissionWithoutMissingRoles(midday.minusHours(1), midday.plusHours(1))
        val missionNotComplete = aMissionWithMissingRoles(midday.minusHours(1), midday.plusHours(1))
        val missionCompleteCommented = missionComplete.copy(hasCommentedInscriptions = true)
        val missionCompleteModified = missionComplete.copy(hasModifiedHoursInscriptions = true)


        `when`(missionService.getAllMissions(aPegassUser, beginLimit, endLimit)).thenReturn(listOf(
                missionComplete,
                missionNotComplete,
                missionCompleteCommented,
                missionCompleteModified
        ))

        // When
        val missionsOtherStructures = missionRepository.getActivities(PegassUser("", ""), beginLimit, endLimit).externalActivities

        // Then
        assertThat(missionsOtherStructures).containsExactlyInAnyOrder(missionNotComplete, missionCompleteCommented, missionCompleteModified)
    }


}
