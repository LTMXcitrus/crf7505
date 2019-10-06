package com.lemonfactory.crf7505.domain.model

data class CrfMail(val sender: String,
                   val recipient: String,
                   val subject: String,
                   val text: String)