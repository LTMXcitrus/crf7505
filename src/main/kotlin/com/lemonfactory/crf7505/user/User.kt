package com.lemonfactory.crf7505.user

import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import org.springframework.security.crypto.bcrypt.BCrypt

fun encrypt(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
}

@Entity
data class User(@Id var username: String = "",
                var firstname: String = "",
                var password: String = "",
                var accessGranted: Boolean = false) {

    fun matchPassword(candidatePassword: String): Boolean {
        return BCrypt.checkpw(candidatePassword, password)
    }
}