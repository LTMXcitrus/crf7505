package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.mission.MissionsDay

class RecapService {

    fun header(): String {
        TODO()
    }

    fun prepareMailBody(recapMissions: List<MissionsDay>): String {
        if(recapMissions.isEmpty()) {
            return ""
        }
        TODO()
    }

    fun footer(): String {
        TODO()
    }

}