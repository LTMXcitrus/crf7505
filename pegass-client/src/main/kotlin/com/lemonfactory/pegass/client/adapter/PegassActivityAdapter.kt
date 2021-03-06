package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.*
import com.lemonfactory.pegass.client.api.activity.ActivitySeance
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.lemonfactory.pegass.client.referentiel.UlReferentiel


class PegassActivityAdapter(
        private val pegassInscriptionAdapter: PegassInscriptionAdapter,
        private val pegassRoleAdapter: PegassRoleAdapter
) {

    fun transform(pegassActivities: List<PegassActivity>): List<Mission> {
        return pegassActivities.flatMap { pegassActivity -> transform(pegassActivity) }
    }

    private fun transform(pegassActivity: PegassActivity): List<Mission> {
        return pegassActivity.seanceList.map { seance -> transform(seance, pegassActivity) }
    }

    private fun transform(seance: ActivitySeance, pegassActivity: PegassActivity): Mission {
        val begin = seance.debut
        val end = seance.fin
        val name = pegassActivity.libelle
        val ul = UlReferentiel.getUlLabel(pegassActivity.structureMenantActivite.id)
        val inscriptions = pegassInscriptionAdapter.transform(seance)
        val roles = pegassRoleAdapter.transform(seance.roleConfigList)
        val activityType = typeActivityOf(pegassActivity.typeActivite.action.id)
        val activityGroup = retrieveActivityGroupFromId(pegassActivity.typeActivite.groupeAction.id)

        return Mission(
                seance.id,
                begin,
                end,
                name,
                ul,
                inscriptions,
                roles,
                findMissingRoles(roles, inscriptions),
                inscriptions.any { it.hasComments },
                inscriptions.any { it.hasModifiedHours },
                activityType,
                activityGroup
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