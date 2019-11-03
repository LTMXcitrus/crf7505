package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.pegass.client.adapter.PegassActivityAdapter
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.lemonfactory.pegass.client.referentiel.UlReferentiel
import java.time.LocalDateTime

class PegassMissionService(val pegassClient: PegassClient, val pegassActivityAdapter: PegassActivityAdapter) : MissionService {
    override fun getActivitiesForStructure(user: PegassUser, start: LocalDateTime, end: LocalDateTime, structure: String): List<Mission> {
        val session = PegassSession(user.username, user.password)
        val structureId = UlReferentiel.getUlIdFromLabel(structure)
        val pegassActivities =
                if (structureId != null) {
                    pegassClient.getActivitiesForStructure(session, start, end, structureId)
                } else {
                    emptyList()
                }.map { activity -> completeActivityWithInscriptions(session, activity) }
        return pegassActivityAdapter.transform(pegassActivities)
    }

    override fun getAllMissions(user: PegassUser, start: LocalDateTime, end: LocalDateTime): List<Mission> {
        val pegassActivities = getAllActivities(user, start, end)
        return pegassActivityAdapter.transform(pegassActivities)
    }

    private fun getAllActivities(user: PegassUser, start: LocalDateTime, end: LocalDateTime): List<PegassActivity> {
        val pegassSession = PegassSession(user.username, user.password)
        return pegassClient.getActivitiesFilteredWithDates(pegassSession, start, end)
                .map { activity -> completeActivityWithInscriptions(pegassSession, activity) }
    }

    private fun completeActivityWithInscriptions(pegassSession: PegassSession, activity: PegassActivity): PegassActivity {
        val seancesWithInscriptions = activity.seanceList.map {
            it.copy(inscriptions = pegassClient.getSeanceInscriptions(pegassSession, it))
        }
        return activity.copy(seanceList = seancesWithInscriptions)
    }

}