package com.lemonfactory.crf7505.security

import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.crf7505.user.ApplicationUser
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceImpl(val applicationUserRepository: ObjectifyDAO<ApplicationUser>) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = applicationUserRepository.findById<ApplicationUser>(username)
                ?: throw UsernameNotFoundException(username)
        if (!applicationUser.accessGranted) {
            throw DisabledException("Account disabled")
        }
        return User(applicationUser.username, applicationUser.password, emptyList())
    }
}
