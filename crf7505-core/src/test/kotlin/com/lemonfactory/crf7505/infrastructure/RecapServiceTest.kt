package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDate.now

class RecapServiceTest {

    private val recapService = RecapService()

    @Test
    fun prepareMailBody_emptyList_returnNoMissionBody() {
        // Given

        // When
        val body = recapService.prepareMailBody(emptyList())

        // Then
        assertThat(body).isEmpty()
    }

    @Test
    fun prepareMailBody_oneMission_returnOneMissionBody() {
        // Given

        // When
        //val body = recapService.prepareMailBody(listOf(MissionsDay(now(),listOf(Mission()))))

        // Then
        //assertThat(body).isEmpty()
    }

}