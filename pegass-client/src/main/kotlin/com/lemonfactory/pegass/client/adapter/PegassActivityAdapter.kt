package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.Inscription
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.domain.model.mission.Role
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
        val inscriptions = pegassInscriptionAdapter.transform(pegassActivity.inscriptions, seance)
        val roles = pegassRoleAdapter.transform(seance.roleConfigList)

        return Mission(
                begin,
                end,
                name,
                ul,
                inscriptions,
                roles,
                findMissingRoles(roles, inscriptions),
                inscriptions.any { it.hasComments },
                inscriptions.any { it.hasModifiedHours }
        )
    }

    private fun findMissingRoles(roles: List<Role>, inscriptions: List<Inscription>): List<Role> {
        return roles
                .map { Role(it.type, countMissingInscriptions(it, inscriptions)) }
                .filter { it.quantity > 0 }
    }

    private fun countMissingInscriptions(role: Role, inscriptions: List<Inscription>): Int {
        return role.quantity - inscriptions.count { it.roleType == role.type }
    }

}