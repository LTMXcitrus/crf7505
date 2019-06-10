package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.pegass.client.api.activity.ActivitySeance
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.lemonfactory.pegass.client.referentiel.UlReferentiel


class PegassActivityAdapter(
        val pegassInscriptionAdapter: PegassInscriptionAdapter,
        val pegassRoleAdapter: PegassRoleAdapter
) {

    fun transform(pegassActivities: List<PegassActivity>): List<Mission> {
        return pegassActivities.flatMap { pegassActivity -> transform(pegassActivity) }
    }

    private fun transform(pegassActivity: PegassActivity): List<Mission> {
        println(pegassActivity.libelle + " " + pegassActivity.seanceList.size)
        return pegassActivity.seanceList.map { seance -> transform(seance, pegassActivity) }
    }

    private fun transform(seance: ActivitySeance, pegassActivity: PegassActivity): Mission {
        val begin = seance.debut
        val end = seance.fin
        val name = pegassActivity.libelle
        val ul = UlReferentiel.getUlLabel(pegassActivity.structureMenantActivite.id)
        val inscriptions = pegassInscriptionAdapter.transform(pegassActivity.inscriptions)
        val roles = pegassRoleAdapter.transform(seance.roleConfigList)

        return Mission(begin, end, name, ul, inscriptions, roles)
    }

}