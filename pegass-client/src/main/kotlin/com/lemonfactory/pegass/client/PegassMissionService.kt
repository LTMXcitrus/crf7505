package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.pegass.client.adapter.PegassActivityAdapter
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.lemonfactory.pegass.client.referentiel.UlReferentiel
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

class PegassMissionService(val pegassClient: PegassClient, val pegassActivityAdapter: PegassActivityAdapter) : MissionService {
    companion object {
        private val logger = LoggerFactory.getLogger(PegassMissionService::class.java)
    }

    private val sessionsMap = mutableMapOf<PegassUser, PegassSession>()

    private fun retrieveSession(user: PegassUser): PegassSession {
        return sessionsMap.getOrElse(user) { PegassSession(user.username, user.password) }
    }

    override fun getActivitiesForStructure(user: PegassUser, start: LocalDateTime, end: LocalDateTime, structure: String): List<Mission> {
        logger.info("retrieve local activities for structure: $structure")
        val session = retrieveSession(user)
        val structureId = UlReferentiel.getUlIdFromLabel(structure)
        val pegassActivities =
                if (structureId != null) {
                    pegassClient.getActivitiesForStructure(session, start, end, structureId)
                } else {
                    emptyList()
                }.map { activity -> completeActivityWithInscriptions(session, activity) }
        return pegassActivityAdapter.transform(pegassActivities)
    }

    override fun getExternalMissions(user: PegassUser, start: LocalDateTime, end: LocalDateTime, structure: String?): List<Mission> {
        logger.info("retrieve external activities for structure: $structure")
        val pegassActivities = getAllActivities(user, start, end, structure ?: "")
        return pegassActivityAdapter.transform(pegassActivities)
    }

    private fun getAllActivities(user: PegassUser, start: LocalDateTime, end: LocalDateTime, structure: String): List<PegassActivity> {
        val pegassSession = retrieveSession(user)
        return pegassClient.getActivitiesFilteredWithDates(pegassSession, start, end)
                .filter { activity -> UlReferentiel.getUlLabel(activity.structureOrganisatrice.id) !== structure }
                .map { activity -> completeActivityWithInscriptions(pegassSession, activity) }
    }

    private fun completeActivityWithInscriptions(pegassSession: PegassSession, activity: PegassActivity): PegassActivity {
        val seancesWithInscriptions = activity.seanceList.map {
            it.copy(inscriptions = pegassClient.getSeanceInscriptions(pegassSession, it))
        }
        return activity.copy(seanceList = seancesWithInscriptions)
    }

    override fun terminate() {
        sessionsMap.clear()
    }

}
