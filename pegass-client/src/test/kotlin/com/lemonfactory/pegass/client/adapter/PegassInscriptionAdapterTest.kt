package com.lemonfactory.pegass.client.adapter


import com.lemonfactory.crf7505.domain.model.mission.RoleType
import com.lemonfactory.pegass.client.testutils.aPegassActivity
import com.lemonfactory.pegass.client.testutils.aPegassInscription
import com.lemonfactory.pegass.client.testutils.anActivitySeance
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime.now

class PegassInscriptionAdapterTest {

    private val adapter = PegassInscriptionAdapter()

    @Test
    fun transform_emptyInscriptions_returnsEmptyList() {
        // Given
        val seance = anActivitySeance()

        // When
        val inscriptions = adapter.transform(seance)
        // Then
        assertThat(inscriptions).isEmpty()
    }

    @Test
    fun transform_singleInscription_returnsAnInscription() {
        // Given
        val pegassInscription = aPegassInscription()
        val seance = anActivitySeance().copy(inscriptions = listOf(pegassInscription))

        // When
        val inscriptions = adapter.transform(seance)

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
        val pegassInscription = aPegassInscription().copy(commentaire = "commentaire")
        val seance = anActivitySeance().copy(inscriptions = listOf(pegassInscription))

        // When
        val inscriptions = adapter.transform(seance)

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
        val beginDate = now()
        val pegassInscription = aPegassInscription().copy(commentaire = "commentaire", debut = beginDate.plusHours(1))
        val seance = anActivitySeance().copy(inscriptions = listOf(pegassInscription), debut = beginDate)

        // When
        val inscriptions = adapter.transform( seance)

        // Then
        assertThat(inscriptions).isNotEmpty
        val inscription = inscriptions[0]
        assertThat(inscription.beginDate).isEqualTo(pegassInscription.debut)
        assertThat(inscription.endDate).isEqualTo(pegassInscription.fin)
        assertThat(inscription.roleType).isEqualTo(RoleType.PSE2)
        assertThat(inscription.hasModifiedHours).isTrue()
    }

}