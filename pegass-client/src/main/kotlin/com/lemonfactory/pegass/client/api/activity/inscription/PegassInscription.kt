package com.lemonfactory.pegass.client.api.activity.inscription

import java.time.LocalDateTime

data class PegassInscription(val debut: LocalDateTime,
                             val fin: LocalDateTime,
                             val id: String,
                             val isMultiple: Boolean,
                             val role: String,
                             val statut: String,
                             val type: String)