package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.Inscription
import com.lemonfactory.crf7505.domain.model.mission.roleTypeFrom
import com.lemonfactory.pegass.client.api.activity.inscription.PegassInscription

class PegassInscriptionAdapter {

    fun transform(pegassInscriptions: List<PegassInscription>): List<Inscription> {
        return pegassInscriptions.map { pegassInscription -> transform(pegassInscription) }
    }

    private fun transform(pegassInscription: PegassInscription): Inscription {
        val roleType = roleTypeFrom(pegassInscription.role, pegassInscription.type)
        return Inscription(pegassInscription.debut, pegassInscription.fin, roleType)
    }

}