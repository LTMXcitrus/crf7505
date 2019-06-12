package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.pegass.client.testutils.anActivityRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PegassRoleAdapterTest {

    val adapter = PegassRoleAdapter()

    @Test
    fun transform_emptyList_returnsEmptyList() {
        // Given

        // When
        val roles = adapter.transform(emptyList())
        // Then
        assertThat(roles).isEmpty()
    }

    @Test
    fun transform_singleActivityRole_returnsCorrectRole() {
        // Given
        val activityRole = anActivityRole().copy(role = "167", type = "FORM")

        // When
        val roles = adapter.transform(listOf(activityRole))
        // Then
        assertThat(roles).isNotEmpty
        assertThat(roles[0].type).isEqualTo(RoleType.PSE2)
        assertThat(roles[0].quantity).isEqualTo(1)
    }

    @Test
    fun transform_twoEqualActivityRoles_returnsCorrectRole() {
        // Given
        val activityRole = anActivityRole().copy(role = "167", type = "FORM", effectif = 2)

        // When
        val roles = adapter.transform(listOf(activityRole))
        // Then
        assertThat(roles).isNotEmpty
        assertThat(roles[0].type).isEqualTo(RoleType.PSE2)
        assertThat(roles[0].quantity).isEqualTo(2)
    }

}