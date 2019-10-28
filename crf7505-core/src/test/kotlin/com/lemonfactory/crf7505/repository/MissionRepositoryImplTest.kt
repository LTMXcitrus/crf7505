package com.lemonfactory.crf7505.repository

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.ActivityGroup
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.infrastructure.ConnectedUserResolver
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.crf7505.user.ApplicationUser
import com.lemonfactory.crf7505.utils.Missions
import com.lemonfactory.crf7505.utils.Missions.aMissionWithMissingRoles
import com.lemonfactory.crf7505.utils.Missions.aMissionWithoutMissingRoles
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
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

    @Before
    fun setUp() {
        `when`(connectedUserResolver.resolveConnectedUser()).thenReturn(ApplicationUser(userStructure = "V"))
    }

    @Test
    fun `getActivities remove out of date range missions`() {
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
        `when`(missionService.getActivitiesForStructure(aPegassUser, beginLimit, endLimit, "V")).thenReturn(listOf(
                missionCompletelyBefore,
                missionPartiallyBefore,
                missionBetween,
                missionPartiallyAfter,
                missionCompletelyAfter,
                missionCovering
        ))

        // When
        val missionsOtherStructures = missionRepository.getActivities(PegassUser("", ""), beginLimit, endLimit).externalActivities
        val localMissions = missionRepository.getActivities(PegassUser("", ""), beginLimit, endLimit).localActivities

        // Then
        assertThat(missionsOtherStructures).containsExactlyInAnyOrder(missionBetween, missionPartiallyAfter)
        assertThat(localMissions).containsExactlyInAnyOrder(missionBetween, missionPartiallyAfter)
    }

    @Test
    fun `getActivities remove complete missions but keep mission with commented or modified inscriptions`() {
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

    @Test
    fun `getActivities localActivities remove Action sociale activities`() {
        // Given

        val beginLimit = midday.minusDays(1).minusHours(2)
        val endLimit = midday.plusDays(1).plusHours(2)

        val asActivity = Missions.aMission(midday.toLocalDate()).copy(activityGroup = ActivityGroup.AS)
        val formationActivity = Missions.aMission(midday.toLocalDate()).copy(activityGroup = ActivityGroup.FORMATION)
        val supportActivity = Missions.aMission(midday.toLocalDate()).copy(activityGroup = ActivityGroup.SOUTIEN_ACTIVITES)
        val usActivity = Missions.aMission(midday.toLocalDate()).copy(activityGroup = ActivityGroup.US)

        `when`(missionService.getActivitiesForStructure(aPegassUser, beginLimit, endLimit, "V")).thenReturn(listOf(
                asActivity,
                formationActivity,
                supportActivity,
                usActivity
        ))

        // When
        val missionsOtherStructures = missionRepository.getActivities(PegassUser("", ""), beginLimit, endLimit).localActivities

        // Then
        assertThat(missionsOtherStructures).containsExactlyInAnyOrder(formationActivity, supportActivity, usActivity)
    }

    @Test
    fun `getActivities externalActivities remove local Activities`() {
        // Given

        val beginLimit = midday.minusDays(1).minusHours(2)
        val endLimit = midday.plusDays(1).plusHours(2)

        val localActivity = Missions.aMission(midday.toLocalDate()).copy(ul = "V")
        val externalActivity1 = Missions.aMission(midday.toLocalDate()).copy(ul = "X")
        val externalActivity2 = Missions.aMission(midday.toLocalDate()).copy(ul = "X")
        val externalActivity3 = Missions.aMission(midday.toLocalDate()).copy(ul = "X")

        `when`(missionService.getAllMissions(aPegassUser, beginLimit, endLimit)).thenReturn(listOf(
                localActivity,
                externalActivity1,
                externalActivity2,
                externalActivity3
        ))

        // When
        val missionsOtherStructures = missionRepository.getActivities(PegassUser("", ""), beginLimit, endLimit).externalActivities

        // Then
        assertThat(missionsOtherStructures).containsExactlyInAnyOrder(externalActivity1, externalActivity2, externalActivity3)
    }


}
