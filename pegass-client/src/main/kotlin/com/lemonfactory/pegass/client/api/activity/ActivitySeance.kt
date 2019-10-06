package com.lemonfactory.pegass.client.api.activity

import com.lemonfactory.pegass.client.api.activity.inscription.PegassInscription
import java.time.LocalDateTime

data class ActivitySeance(val id: String,
                          val activite: Activity,
                          val groupeAction: ActionGroup,
                          val debut: LocalDateTime,
                          val fin: LocalDateTime,
                          val adresse: String,
                          val roleConfigList: List<ActivityRole>,
                          val inscriptions: List<PegassInscription> = emptyList())

