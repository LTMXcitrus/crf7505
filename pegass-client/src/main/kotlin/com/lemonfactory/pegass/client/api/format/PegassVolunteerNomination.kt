package com.lemonfactory.pegass.client.api.format

import java.time.LocalDateTime

data class PegassVolunteerNomination(val dateValidation: LocalDateTime,
                                     val nominationGroup: PegassNominationGroup,
                                     val id: String,
                                     val libelleCourt: String,
                                     val libelleLong: String)