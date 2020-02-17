package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.domain.model.PegassUser

class PegassApiService(val pegassClient: PegassClient) {

    fun callPegassApi(user: PegassUser, url: String): Any {
        val session = PegassSession(user.username, user.password)
        return pegassClient.getResponseForUrl(session, url)
    }

}
