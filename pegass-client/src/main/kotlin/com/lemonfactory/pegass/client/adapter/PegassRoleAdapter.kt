package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.mission.Role
import com.lemonfactory.crf7505.domain.model.mission.roleTypeFrom
import com.lemonfactory.pegass.client.api.activity.ActivityRole

class PegassRoleAdapter {

    fun transform(roles: List<ActivityRole>): List<Role> {
        return roles.map { role -> transform(role) }
    }

    fun transform(role: ActivityRole): Role {
        val roleType = roleTypeFrom(role.role, role.type ?: "")
        return Role(roleType, role.effectif)
    }

}