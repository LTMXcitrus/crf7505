package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.domain.model.Mission
import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.pegass.client.adapter.PegassActivityAdapter
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import java.time.LocalDate

class PegassMissionService(val pegassClient: PegassClient, val pegassTrainingAdapter: PegassActivityAdapter) : MissionService {

    override fun getAllMissions(user: PegassUser, start: LocalDate, end: LocalDate): List<Mission> {
        val pegassActivities = getAllActivities(user, start, end)
        return pegassTrainingAdapter.transform(pegassActivities)
    }

    private fun getAllActivities(user: PegassUser, start: LocalDate, end: LocalDate): List<PegassActivity> {
        val pegassSession = PegassSession(user.username, user.password)
        return pegassClient.getActivities(pegassSession, start, end)
    }

}