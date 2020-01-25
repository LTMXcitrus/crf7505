package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.model.mission.Role
import com.lemonfactory.crf7505.model.mission.roleTypeFrom
import com.lemonfactory.pegass.client.api.activity.ActivityRole

class PegassRoleAdapter {

    fun transform(roles: List<ActivityRole>): List<Role> {
        return roles.mapNotNull { role -> transform(role) }
    }

    fun transform(role: ActivityRole): Role? {
        if (role.effectif == 0) {
            return null
        }
        val roleType = roleTypeFrom(role.type ?: "", role.code)
        return Role(roleType, role.effectif)
    }

}