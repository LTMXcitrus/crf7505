package com.lemonfactory.crf7505.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.lemonfactory.crf7505.domain.model.mission.RoleType

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class Volunteer(
        @Id var emailAddress: String = "",
        var firstname: String = "",
        var lastname: String = "",
        var role: RoleType = RoleType.PARTICIPANT,
        var interestedIn: List<RoleType> = emptyList()
) : ObjectifyElement {

    override fun getId() = emailAddress

}