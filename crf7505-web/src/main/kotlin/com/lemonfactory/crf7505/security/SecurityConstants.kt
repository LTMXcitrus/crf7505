package com.lemonfactory.crf7505.security

object SecurityConstants {
    val EXPIRATION_TIME: Long = 864000000 // 10 days
    val TOKEN_PREFIX = "Bearer "
    val HEADER_STRING = "Authorization"
    val SIGN_UP_URL = "/users/sign-up"
    val EXPOSE_AUTHORIZATION_HEADERS = "Access-Control-Expose-Headers"
}