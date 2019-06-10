package com.lemonfactory.pegass.client.adapter


import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.pegass.client.testutils.aPegassInscription
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PegassInscriptionAdapterTest {

    private val adapter = PegassInscriptionAdapter()

    @Test
    fun transform_emptyInscriptions_returnsEmptyList(){
        // Given

        // When
        val inscriptions = adapter.transform(emptyList())
        // Then
        assertThat(inscriptions).isEmpty()
    }

    @Test
    fun transform_singleInscription_returnsAnInscription(){
        // Given
        val pegassInscription = aPegassInscription()

        // When
        val inscriptions = adapter.transform(listOf(pegassInscription))
        
        // Then
        assertThat(inscriptions).isNotEmpty
        val inscription = inscriptions[0]
        assertThat(inscription.beginDate).isEqualTo(pegassInscription.debut)
        assertThat(inscription.endDate).isEqualTo(pegassInscription.fin)
        assertThat(inscription.roleType).isEqualTo(RoleType.PSE2)
    }

}