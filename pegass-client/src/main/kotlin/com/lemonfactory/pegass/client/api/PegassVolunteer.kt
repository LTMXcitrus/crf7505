package com.lemonfactory.pegass.client.api

data class PegassVolunteer(val id: String,
                           val actif: Boolean,
                           val mineur: Boolean,
                           val nom: String?,
                           val prenom: String?)