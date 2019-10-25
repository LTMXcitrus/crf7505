package com.lemonfactory.crf7505.security

import com.lemonfactory.crf7505.infrastructure.ConnectedUserResolver
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.crf7505.user.ApplicationUser
import org.springframework.security.core.context.SecurityContextHolder

class ConnectedUserResolverImpl(val applicationUserDao : ObjectifyDAO<ApplicationUser>) : ConnectedUserResolver {

    override fun resolveConnectedUser(): ApplicationUser? {
        val connectedUsername = SecurityContextHolder.getContext().authentication.principal
        if(connectedUsername is String) {
            return applicationUserDao.findById<ApplicationUser>(connectedUsername)
        }
        return null
    }

}