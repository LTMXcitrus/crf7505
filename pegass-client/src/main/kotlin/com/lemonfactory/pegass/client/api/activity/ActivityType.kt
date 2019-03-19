package com.lemonfactory.pegass.client.api.activity

data class ActivityType(val id: Int,
                        val libelle: String,
                        val groupeAction: ActionGroup,
                        val action: Action)