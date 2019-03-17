package com.lemonfactory.pegass.client.referentiel

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UlReferentielTest {


    @Test
    fun getAllUlIds_returnsAllUlIds() {
        val ulIds = UlReferentiel.getAllUlIds()

        assertThat(ulIds).isNotEmpty
    }
}