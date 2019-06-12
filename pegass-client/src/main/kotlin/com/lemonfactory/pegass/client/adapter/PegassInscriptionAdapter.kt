package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.Inscription
import com.lemonfactory.crf7505.domain.model.mission.roleTypeFrom
import com.lemonfactory.pegass.client.api.activity.ActivitySeance
import com.lemonfactory.pegass.client.api.activity.inscription.PegassInscription

class PegassInscriptionAdapter {

    fun transform(pegassInscriptions: List<PegassInscription>, seance: ActivitySeance): List<Inscription> {
        return pegassInscriptions.map { pegassInscription -> transform(pegassInscription, seance) }
    }

    private fun transform(pegassInscription: PegassInscription, seance: ActivitySeance): Inscription {
        val roleType = roleTypeFrom(pegassInscription.role, pegassInscription.type)
        return Inscription(
                pegassInscription.debut,
                pegassInscription.fin,
                roleType,
                pegassInscription.commentaire?.isNotBlank() ?: false,
                hasModifiedHours(pegassInscription, seance)
        )
    }

    private fun hasModifiedHours(pegassInscription: PegassInscription, seance: ActivitySeance): Boolean {
        return pegassInscription.debut != seance.debut || pegassInscription.fin != seance.fin
    }

}