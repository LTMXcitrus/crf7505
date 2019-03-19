package com.lemonfactory.pegass.client.api.format

import java.time.LocalDateTime

data class PegassVolunteerTraining(val dateObtention: LocalDateTime,
                                   val dateRecyclage: LocalDateTime?,
                                   val formation: PegassTraining)