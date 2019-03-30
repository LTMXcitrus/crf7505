package com.lemonfactory.crf7505.domain.model

import java.time.LocalDate

data class Training(val obtainedDate: LocalDate,
                    val recyclingDate: LocalDate?,
                    val code: String,
                    val libelle: String,
                    val upToDate: Boolean)