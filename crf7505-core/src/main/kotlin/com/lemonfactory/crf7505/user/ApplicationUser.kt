package com.lemonfactory.crf7505.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.lemonfactory.crf7505.model.ObjectifyElement


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class ApplicationUser(@Id var username: String = "",
                           var firstname: String = "",
                           var password: String = "",
                           var userStructure: String = "",
                           var accessGranted: Boolean = false) : ObjectifyElement {
    override fun getId() = username
}
