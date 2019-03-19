package com.lemonfactory.pegass.client.api.activity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Action(val id: Int,
                  val libelle: String,
                  val groupeAction: ActionGroup)