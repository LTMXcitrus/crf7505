package com.lemonfactory.crf7505.domain.model.mission

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RoleTypeTest {

    @Test
    fun roleTypeFrom_PegassRolePSC1_returnPSC1() {
        // Given

        // When
        val role = roleTypeFrom( "FORM", "171")

        // Then
        assertThat(role).isEqualTo(RoleType.PSC1)
    }

    @Test
    fun roleTypeFrom_baseContactRoleSecouriste_returnPSE1() {
        // Given

        // When
        val role = roleTypeFrom("", "SEC")

        // Then
        assertThat(role).isEqualTo(RoleType.PSE1)
    }

    @Test
    fun roleTypeFrom_pegassRoleSecouriste_returnPSE1() {
        // Given

        // When
        val role = roleTypeFrom("FORM", "166")

        // Then
        assertThat(role).isEqualTo(RoleType.PSE1)
    }

    @Test
    fun roleTypeFrom_pegassRoleOnyx_returnOnyx() {
        // Given

        // When
        val role = roleTypeFrom("COMP", "7")

        // Then
        assertThat(role).isEqualTo(RoleType.ONYX)
    }

    @Test
    fun roleTypeFrom_baseContactRoleStagiare_returnPSC1() {
        // Given

        // When
        val role = roleTypeFrom("", "S")

        // Then
        assertThat(role).isEqualTo(RoleType.PSC1)
    }

    @Test
    fun roleTypeFrom_baseContactRoleLogisticien_returnLogisticien() {
        // Given

        // When
        val role = roleTypeFrom("", "L")

        // Then
        assertThat(role).isEqualTo(RoleType.LOGISTICIEN)
    }

    @Test
    fun roleTypeFrom_baseContactRoleCI_returnCI() {
        // Given

        // When
        val role = roleTypeFrom("", "CI")

        // Then
        assertThat(role).isEqualTo(RoleType.CI)
    }

    @Test
    fun roleTypeFrom_baseContactRoleCDP_returnCDP() {
        // Given

        // When
        val role = roleTypeFrom("", "CP")

        // Then
        assertThat(role).isEqualTo(RoleType.CDP)
    }

    @Test
    fun roleTypeFrom_baseContactRolePSE2_returnPSE2() {
        // Given

        // When
        val role = roleTypeFrom("", "ES")

        // Then
        assertThat(role).isEqualTo(RoleType.PSE2)
    }

}