package com.lemonfactory.crf7505.infrastructure.security

import com.lemonfactory.crf7505.user.ApplicationUserRepository
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceImpl(val applicationUserRepository: ApplicationUserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = applicationUserRepository.findByUsername(username)
                ?: throw UsernameNotFoundException(username)
        if (!applicationUser.accessGranted) {
            throw DisabledException("Account disabled")
        }
        return User(applicationUser.username, applicationUser.password, emptyList())
    }
}