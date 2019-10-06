package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.Role
import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.pegass.client.testutils.aPegassActivity
import com.lemonfactory.pegass.client.testutils.aPegassInscription
import com.lemonfactory.pegass.client.testutils.anActivityRole
import com.lemonfactory.pegass.client.testutils.anActivitySeance
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime

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
                roleConfigList = listOf(anActivityRole()),
                inscriptions = listOf(aPegassInscription())
        )
        val activity = aPegassActivity().copy(
                seanceList = listOf(activitySeance)
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
        assertThat(mission.id).isEqualTo("seance-code")
        assertThat(mission.roles).isNotEmpty
    }

    @Test
    fun getMissingRoles_noInscription_returnsAllEmptySpot() {
        // Given
        val roles = listOf(
                anActivityRole().copy(type = RoleType.PSE2.type, role = RoleType.PSE2.code),
                anActivityRole().copy(type = RoleType.PSE1.type, role = RoleType.PSE1.code)
        )
        val activitySeance = anActivitySeance().copy(
                roleConfigList = roles,
                inscriptions = emptyList()
        )
        val activity = aPegassActivity().copy(
                seanceList = listOf(activitySeance)
        )

        // When
        val missions = adapter.transform(listOf(activity))

        // Then
        assertThat(missions).hasSize(1)
        val missingRoles = missions[0].missingRoles
        assertThat(missingRoles).hasSize(2)
        assertThat(missingRoles).isEqualTo(
                listOf(
                        Role(RoleType.PSE2, 1),
                        Role(RoleType.PSE1, 1)
                )
        )
    }

    @Test
    fun getMissingRoles_missionComplete_returnsEmptyList() {
        // Given
        val beginDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusHours(8)
        val roles = listOf(
                anActivityRole().copy(type = RoleType.PSE2.type, role = RoleType.PSE2.code),
                anActivityRole().copy(type = RoleType.PSE1.type, role = RoleType.PSE1.code)
        )
        val inscriptions = listOf(
                aPegassInscription().copy(debut = beginDate, fin = endDate, type = RoleType.PSE2.type, role = RoleType.PSE2.code),
                aPegassInscription().copy(debut = beginDate, fin = endDate, type = RoleType.PSE1.type, role = RoleType.PSE1.code)
        )
        val activitySeance = anActivitySeance().copy(
                debut = beginDate,
                fin = endDate,
                roleConfigList = roles,
                inscriptions = inscriptions
        )
        val activity = aPegassActivity().copy(
                seanceList = listOf(activitySeance)
        )

        // When
        val missions = adapter.transform(listOf(activity))

        // Then
        assertThat(missions).hasSize(1)
        val missingRoles = missions[0].missingRoles
        assertThat(missingRoles).isEmpty()
    }

    @Test
    fun getMissingRoles_missionNotComplete_returnsMissingRoles() {
        // Given
        val beginDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusHours(8)
        val roles = listOf(
                anActivityRole().copy(type = RoleType.PSE2.type, role = RoleType.PSE2.code, effectif = 2),
                anActivityRole().copy(type = RoleType.PSE1.type, role = RoleType.PSE1.code),
                anActivityRole().copy(type = RoleType.CI.type, role = RoleType.CI.code)
        )
        val inscriptions = listOf(
                aPegassInscription().copy(debut = beginDate, fin = endDate, type = RoleType.PSE1.type, role = RoleType.PSE1.code)
        )
        val activitySeance = anActivitySeance().copy(
                debut = beginDate,
                fin = endDate,
                roleConfigList = roles,
                inscriptions = inscriptions
        )
        val activity = aPegassActivity().copy(
                seanceList = listOf(activitySeance)
        )

        // When
        val missions = adapter.transform(listOf(activity))

        // Then
        assertThat(missions).hasSize(1)
        val missingRoles = missions[0].missingRoles
        assertThat(missingRoles).containsExactlyInAnyOrder(
                Role(RoleType.PSE2, 2),
                Role(RoleType.CI, 1)
        )
    }

}