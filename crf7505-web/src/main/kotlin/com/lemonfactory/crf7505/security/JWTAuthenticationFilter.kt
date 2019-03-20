package com.lemonfactory.crf7505.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.authentication.AuthenticationManager
import javax.servlet.ServletException
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import com.fasterxml.jackson.databind.ObjectMapper
import com.lemonfactory.crf7505.security.SecurityConstants.EXPIRATION_TIME
import com.lemonfactory.crf7505.security.SecurityConstants.EXPOSE_AUTHORIZATION_HEADERS
import com.lemonfactory.crf7505.security.SecurityConstants.HEADER_STRING
import com.lemonfactory.crf7505.security.SecurityConstants.TOKEN_PREFIX
import com.lemonfactory.crf7505.security.user.ApplicationUser
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.*


class JWTAuthenticationFilter(private val theAuthenticationManager: AuthenticationManager, private val secret: String) : UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(req: HttpServletRequest,
                                       res: HttpServletResponse?): Authentication {
        try {
            val creds = ObjectMapper()
                    .readValue(req.inputStream, ApplicationUser::class.java)

            return theAuthenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            creds.username,
                            creds.password,
                            ArrayList<GrantedAuthority>())
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest,
                                          res: HttpServletResponse,
                                          chain: FilterChain,
                                          auth: Authentication) {
        val token = JWT.create()
                .withSubject((auth.principal as User).username)
                .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(secret.toByteArray()))
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
        res.addHeader(EXPOSE_AUTHORIZATION_HEADERS, HEADER_STRING)
    }

}