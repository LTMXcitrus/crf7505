package com.lemonfactory.crf7505.config

import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.lemonfactory.crf7505.domain.model.ObjectifyElement

@Entity
data class ConfigValue(@Id val name: String = "", val value: String = "") : ObjectifyElement {

    override fun getId(): String {
        return name
    }

}