package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.user.ApplicationUser

interface ConnectedUserResolver {

    fun resolveConnectedUser(username: String? = null): ApplicationUser?

}
