package com.lemonfactory.pegass.client.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ActionGroup(val libelle: String,
                       val tri: String?,
                       val id: Int)
