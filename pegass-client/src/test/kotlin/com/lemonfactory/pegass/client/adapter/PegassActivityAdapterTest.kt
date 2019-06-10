package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.pegass.client.testutils.aPegassActivity
import com.lemonfactory.pegass.client.testutils.aPegassInscription
import com.lemonfactory.pegass.client.testutils.anActivityRole
import com.lemonfactory.pegass.client.testutils.anActivitySeance
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PegassActivityAdapterTest {

    private val pegassInscriptionAdapter = PegassInscriptionAdapter()
    private val pegassRoleAdapter = PegassRoleAdapter()
    private val adapter = PegassActivityAdapter(pegassInscriptionAdapter, pegassRoleAdapter)

    @Test
    fun transform_noActivity_returnEmptyList() {
        // Given

        // When
        val missions = adapter.transform(emptyList())

        // Then
        assertThat(missions).isEmpty()
    }

    @Test
    fun transform_emptySeancesListPegassActivity_returnsEmptyList() {
        // Given
        val activity = aPegassActivity().copy(seanceList = emptyList())

        // When
        val missions = adapter.transform(listOf(activity))

        // Then
        assertThat(missions).isEmpty()
    }

    @Test
    fun transform_singlePegassActivity_returnsMission() {
        // Given
        val activitySeance = anActivitySeance().copy(
                roleConfigList = listOf(anActivityRole())
        )
        val activity = aPegassActivity().copy(
                seanceList = listOf(activitySeance),
                inscriptions = listOf(aPegassInscription())
        )

        // When
        val missions = adapter.transform(listOf(activity))

        // Then
        assertThat(missions).hasSize(1)
        val mission = missions[0]
        assertThat(mission.beginDate).isEqualTo(activity.seanceList[0].debut)
        assertThat(mission.endDate).isEqualTo(activity.seanceList[0].fin)
        assertThat(mission.name).isEqualToIgnoringCase(activity.libelle)
        assertThat(mission.ul).isEqualTo("V")
        assertThat(mission.inscriptions).isNotEmpty
        assertThat(mission.roles).isNotEmpty
    }

}