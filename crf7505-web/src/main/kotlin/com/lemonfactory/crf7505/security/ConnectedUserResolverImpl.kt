package com.lemonfactory.crf7505.security

import com.lemonfactory.crf7505.infrastructure.ConnectedUserResolver
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.crf7505.user.ApplicationUser
import org.springframework.security.core.context.SecurityContextHolder

class ConnectedUserResolverImpl(val applicationUserDao : ObjectifyDAO<ApplicationUser>) : ConnectedUserResolver {

    override fun resolveConnectedUser(username: String?): ApplicationUser? {
        val connectedUsername = if(username.isNullOrBlank()) {
            SecurityContextHolder.getContext().authentication.principal
        } else {
            username!!
        }
        if(connectedUsername is String) {
            return applicationUserDao.findById<ApplicationUser>(connectedUsername)
        }
        return null
    }

}
