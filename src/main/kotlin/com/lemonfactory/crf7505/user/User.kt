package com.lemonfactory.crf7505.user

import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id

@Entity
data class User(@Id val username: String,
                val firstname: String,
                val password: String)