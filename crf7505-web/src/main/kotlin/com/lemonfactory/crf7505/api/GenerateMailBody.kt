package com.lemonfactory.crf7505.api

import com.lemonfactory.crf7505.model.Activities

data class GenerateMailBody(val subject: String,
                            val header: String,
                            val footer: String,
                            val activities: Activities,
                            val respMission: String) {
}
