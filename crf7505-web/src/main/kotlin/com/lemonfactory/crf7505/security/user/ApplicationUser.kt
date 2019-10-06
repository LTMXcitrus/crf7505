package com.lemonfactory.crf7505.security.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.lemonfactory.crf7505.domain.model.ObjectifyElement
import org.springframework.security.crypto.bcrypt.BCrypt

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class ApplicationUser(@Id var username: String = "",
                           var firstname: String = "",
                           var password: String = "",
                           var accessGranted: Boolean = false) : ObjectifyElement {
    override fun getId() = username

    fun matchPassword(candidatePassword: String): Boolean {
        return BCrypt.checkpw(candidatePassword, password)
    }
}