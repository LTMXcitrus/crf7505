package com.lemonfactory.crf7505.security

object SecurityConstants {
    val EXPIRATION_TIME: Long = 86400000000 // 1000 days
    val TOKEN_PREFIX = "Bearer "
    val HEADER_STRING = "Authorization"
    val SIGN_UP_URL = "/users/sign-up"
    val EXPOSE_AUTHORIZATION_HEADERS = "Access-Control-Expose-Headers"
}
