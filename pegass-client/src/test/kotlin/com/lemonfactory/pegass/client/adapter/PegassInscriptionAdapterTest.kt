package com.lemonfactory.pegass.client.adapter


import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.pegass.client.testutils.aPegassActivity
import com.lemonfactory.pegass.client.testutils.aPegassInscription
import com.lemonfactory.pegass.client.testutils.anActivitySeance
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PegassInscriptionAdapterTest {

    private val adapter = PegassInscriptionAdapter()

    @Test
    fun transform_emptyInscriptions_returnsEmptyList() {
        // Given
        val seance = anActivitySeance()

        // When
        val inscriptions = adapter.transform(emptyList(), seance)
        // Then
        assertThat(inscriptions).isEmpty()
    }

    @Test
    fun transform_singleInscription_returnsAnInscription() {
        // Given
        val seance = anActivitySeance()
        val pegassInscription = aPegassInscription()

        // When
        val inscriptions = adapter.transform(listOf(pegassInscription), seance)

        // Then
        assertThat(inscriptions).isNotEmpty
        val inscription = inscriptions[0]
        assertThat(inscription.beginDate).isEqualTo(pegassInscription.debut)
        assertThat(inscription.endDate).isEqualTo(pegassInscription.fin)
        assertThat(inscription.roleType).isEqualTo(RoleType.PSE2)
    }

    @Test
    fun transform_singleInscriptionWithComments_returnsAnInscriptionWithCommentFlag() {
        // Given
        val seance = anActivitySeance()
        val pegassInscription = aPegassInscription().copy(commentaire = "commentaire")

        // When
        val inscriptions = adapter.transform(listOf(pegassInscription), seance)

        // Then
        assertThat(inscriptions).isNotEmpty
        val inscription = inscriptions[0]
        assertThat(inscription.beginDate).isEqualTo(pegassInscription.debut)
        assertThat(inscription.endDate).isEqualTo(pegassInscription.fin)
        assertThat(inscription.roleType).isEqualTo(RoleType.PSE2)
        assertThat(inscription.hasComments).isTrue()
    }

    @Test
    fun transform_singleInscriptionWithModifiedHours_returnsAnInscriptionWithModifiedHoursFlag() {
        // Given
        val seance = anActivitySeance()
        val pegassInscription = aPegassInscription().copy(commentaire = "commentaire", debut = seance.debut.plusHours(1))

        // When
        val inscriptions = adapter.transform(listOf(pegassInscription), seance)

        // Then
        assertThat(inscriptions).isNotEmpty
        val inscription = inscriptions[0]
        assertThat(inscription.beginDate).isEqualTo(pegassInscription.debut)
        assertThat(inscription.endDate).isEqualTo(pegassInscription.fin)
        assertThat(inscription.roleType).isEqualTo(RoleType.PSE2)
        assertThat(inscription.hasModifiedHours).isTrue()
    }

}