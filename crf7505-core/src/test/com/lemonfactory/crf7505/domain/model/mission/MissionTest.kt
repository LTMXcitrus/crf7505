package com.lemonfactory.crf7505.domain.model.mission

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime.now

class MissionTest {

    @Test
    fun getMissingInscriptions_noInscription_returnsAllEmptySpot() {
        // Given
        val beginDate = now()
        val endDate = now().plusHours(8)
        val mission = aMission(beginDate, endDate)
                .copy(
                        roles = listOf(
                                Role(RoleType.PSE2, 1),
                                Role(RoleType.PSE1, 1)
                        )
                )

        // When
        val missingInscriptions = mission.getMissionInscription()

        // Then
        assertThat(missingInscriptions).hasSize(2)
        assertThat(missingInscriptions).allMatch { inscription ->
            inscription.beginDate == beginDate && inscription.endDate == endDate
        }
        assertThat(missingInscriptions)
                .anyMatch { inscription ->
                    inscription.roleType == RoleType.PSE2
                }
        assertThat(missingInscriptions)
                .anyMatch { inscription ->
                    inscription.roleType == RoleType.PSE1
                }
    }

    @Test
    fun getMissingInscriptions_missionComplete_returnsEmptyList() {
        // Given
        val beginDate = now()
        val endDate = now().plusHours(8)
        val mission = aMission(beginDate, endDate)
                .copy(
                        roles = listOf(
                                Role(RoleType.PSE2, 1),
                                Role(RoleType.PSE1, 1)
                        ),
                        inscriptions = listOf(
                                Inscription(beginDate, endDate, RoleType.PSE2),
                                Inscription(beginDate, endDate, RoleType.PSE1)
                        )
                )

        // When
        val missingInscriptions = mission.getMissionInscription()

        // Then
        assertThat(missingInscriptions).isEmpty()
    }


}