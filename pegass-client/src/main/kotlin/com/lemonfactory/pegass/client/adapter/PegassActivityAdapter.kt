package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.Mission
import com.lemonfactory.crf7505.domain.model.Role
import com.lemonfactory.pegass.client.api.activity.ActivityRole
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.lemonfactory.pegass.client.referentiel.UlReferentiel

class PegassActivityAdapter() {

    fun transform(pegassActivities: List<PegassActivity>): List<Mission> {
        return pegassActivities
                .flatMap { pegassActivity -> transform(pegassActivity) }
    }

    private fun transform(pegassActivity: PegassActivity): List<Mission> {
        val ulName = UlReferentiel.getUlLabel(pegassActivity.structureMenantActivite.id)
        return pegassActivity.seanceList.map { seance ->
            val roles = transformRoles(seance.roleConfigList)
            Mission(seance.id, seance.debut, seance.fin, ulName, pegassActivity.libelle, roles)
        }
    }

    private fun transformRoles(roleConfigList: List<ActivityRole>): List<Role> {
        return roleConfigList
                .map { role -> Role(role.code, role.type ?: "", role.effectif) }
    }
}