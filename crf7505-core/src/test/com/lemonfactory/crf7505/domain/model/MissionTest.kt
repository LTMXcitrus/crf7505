package com.lemonfactory.crf7505.domain.model


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime

class MissionTest {

    @Test
    fun getCommonId_uniqueMission_returnId() {
        val id = "p552981"
        val mission = aMission(id)

        val commonId = mission.getCommonId()

        assertThat(commonId).isEqualTo(id)
    }

    @Test
    fun getCommonId_severalDaysMission_returnCommonId() {
        val id = "p552981-1"
        val mission = aMission(id)

        val commonId = mission.getCommonId()

        assertThat(commonId).isEqualTo("p552981")
    }

    private fun aMission(id: String) = Mission(id, LocalDateTime.now(), LocalDateTime.now(), "ul", "name", emptyList())

}