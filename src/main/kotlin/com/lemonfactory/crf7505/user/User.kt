package com.lemonfactory.crf7505.user

import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.googlecode.objectify.annotation.Index

@Entity
@Index
data class User(@Id val username: String?,
                val firstname: String?,
                val password: String?) {
    constructor(): this(null, null, null)
}