package com.lemonfactory.pegass.client.api

data class ActivityType(val id: Int,
                        val libelle: String,
                        val groupeAction: ActionGroup,
                        val action: Action)