package com.lemonfactory.crf7505.domain.model

import com.lemonfactory.crf7505.domain.model.mission.RoleType

data class Volunteer(val firstname: String,
                     val lastname: String,
                     val role: RoleType,
                     val emailAddress: String)