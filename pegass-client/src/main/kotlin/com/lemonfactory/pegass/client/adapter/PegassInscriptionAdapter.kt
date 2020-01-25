package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.model.mission.Inscription
import com.lemonfactory.crf7505.model.mission.roleTypeFrom
import com.lemonfactory.pegass.client.api.activity.ActivitySeance
import com.lemonfactory.pegass.client.api.activity.inscription.PegassInscription

class PegassInscriptionAdapter {

    fun transform(seance: ActivitySeance): List<Inscription> {
        return seance.inscriptions
                .map { pegassInscription -> transform(pegassInscription, seance) }
    }

    private fun transform(pegassInscription: PegassInscription, seance: ActivitySeance): Inscription {
        val roleType = roleTypeFrom(pegassInscription.type, pegassInscription.role)
        return Inscription(
                pegassInscription.debut,
                pegassInscription.fin,
                roleType,
                pegassInscription.commentaire?.isNotBlank() ?: false,
                hasModifiedHours(pegassInscription, seance),
                pegassInscription.utilisateur.id
        )
    }

    private fun hasModifiedHours(pegassInscription: PegassInscription, seance: ActivitySeance): Boolean {
        return pegassInscription.debut != seance.debut || pegassInscription.fin != seance.fin
    }

}