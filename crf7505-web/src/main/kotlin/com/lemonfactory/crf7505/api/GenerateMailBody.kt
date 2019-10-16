package com.lemonfactory.crf7505.api

import com.lemonfactory.crf7505.domain.model.mission.Mission

data class GenerateMailBody(val subject: String,
                            val header: String,
                            val footer: String,
                            val missions: List<Mission>)
