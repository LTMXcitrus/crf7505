package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.pegass.client.testutils.aPegassActivity
import com.lemonfactory.pegass.client.testutils.anActivitySeance
import org.assertj.core.api.Assertions.assertThat

import org.junit.Test
import java.time.LocalDateTime
import java.util.function.Predicate

class PegassActivityAdapterTest {

    val adapter = PegassActivityAdapter()


    @Test
    fun transform_singlePegassActivity_returnsMissionDay() {
        // Given
        val pegassActivity = aPegassActivity().copy(seanceList = listOf(anActivitySeance()))

        // When
        val missionDays = adapter.transform(listOf(pegassActivity))

        // Then
        assertThat(missionDays).isNotEmpty
    }

    @Test
    fun transform_twoPegassActivitiesWithSingleSeance_returnsOneMissionDayWithTwoMissions() {
        // Given
        val pegassActivity = aPegassActivity().copy(seanceList = listOf(anActivitySeance()))

        // When
        val missionDays = adapter.transform(listOf(pegassActivity, pegassActivity))

        // Then
        assertThat(missionDays).hasSize(1)
        assertThat(missionDays[0].missions).hasSize(2)
    }

    @Test
    fun transform_pegassActivitiesWithTwoSeances_returnsTwoMissionDaysWithOneMission() {
        // Given
        val firstSeance = anActivitySeance()
        val secondSeance = anActivitySeance().copy(debut = LocalDateTime.now().plusDays(1), fin = LocalDateTime.now().plusDays(1))
        val pegassActivity = aPegassActivity().copy(seanceList = listOf(firstSeance, secondSeance))

        // When
        val missionDays = adapter.transform(listOf(pegassActivity))

        // Then
        assertThat(missionDays).hasSize(2)
        assertThat(missionDays).allMatch { mission -> mission.missions.size == 1 }
    }
}