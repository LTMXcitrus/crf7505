package com.lemonfactory.pegass.client.api.activity

import java.time.LocalDateTime

data class ActivityReccurrence(val id: String,
                               val creationEnMasse: Boolean,
                               val type: String,
                               val creneauRecurrentList: List<Any>,
                               val debut: LocalDateTime,
                               val fin: LocalDateTime)