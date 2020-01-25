package com.lemonfactory.pegass.client.stats

import com.lemonfactory.crf7505.infrastructure.StatsQuery
import com.lemonfactory.crf7505.infrastructure.StatsService
import com.lemonfactory.crf7505.model.mission.Mission
import com.lemonfactory.crf7505.model.stats.bdd.VolunteerStats
import com.lemonfactory.pegass.client.PegassClient
import com.lemonfactory.pegass.client.PegassSession
import com.lemonfactory.pegass.client.adapter.PegassActivityAdapter
import com.lemonfactory.pegass.client.adapter.PegassInscriptionAdapter
import com.lemonfactory.pegass.client.adapter.PegassRoleAdapter
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class PegassStatsService(
         val pegassClient: PegassClient,
        private val pegassActivityAdapter: PegassActivityAdapter
) : StatsService {

    private val LOGGER = LoggerFactory.getLogger(PegassStatsService::class.java)


    override fun loadStats(statsQuery: StatsQuery): List<VolunteerStats> {
        return getVolunteersActivities(statsQuery)
                .flatMap { pegassActivity -> pegassActivity.inscriptions.map { Pair(it.nivol, pegassActivity) } }.groupBy { it.first }
                .map { entry -> entry.key to entry.value.map { it.second } }
                .map { pair -> VolunteerStats(pair.first, pair.second) }
    }

    private fun getVolunteersActivities(statsQuery: StatsQuery): List<Mission> {
        val structureActivities = with(statsQuery) {
            val pegassSession = PegassSession(username, password)
            pegassClient.getActivities(pegassSession, beginDate.atStartOfDay(), endDate.atStartOfDay())
                    .mapNotNull { activity ->
                        completeActivityWithInscriptionsFrom(nivols, pegassSession, activity)
                    }
                    .filter { activity -> activity.seanceList.any { seance -> seance.inscriptions.any { nivols.contains(it.utilisateur.id) } } }
        }
        LOGGER.info("Loading from pegass is complete")
        return pegassActivityAdapter.transform(structureActivities)
    }

    private fun completeActivityWithInscriptionsFrom(nivols: List<String>, pegassSession: PegassSession, activity: PegassActivity): PegassActivity? {
        LOGGER.info("completing activity ${activity.libelle} with inscriptions")
        val seancesWithInscriptions = activity.seanceList.mapNotNull {
            val inscriptions = pegassClient.getSeanceInscriptions(pegassSession, it)
                    .filter { inscription -> nivols.contains(inscription.utilisateur.id) }
            if (inscriptions.isEmpty()) {
                null
            } else {
                it.copy(inscriptions = inscriptions)
            }
        }
        if (seancesWithInscriptions.isEmpty()) {
            return null
        }
        return activity.copy(seanceList = seancesWithInscriptions)
    }
}

fun main() {
    val service = PegassStatsService(PegassClient(), PegassActivityAdapter(PegassInscriptionAdapter(), PegassRoleAdapter()))
    val volunteers = service.pegassClient.getVolunteers(PegassSession("lemonnierma", "")).map { it.id }
    val query = StatsQuery(
            "lemonnierma",
            "",
            LocalDate.parse("2019-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            LocalDate.now(),
            listOf("01000009046N")//volunteers
    )
    val stats = service.loadStats(query)
    println(volunteers)
    println(volunteers.count())

    //val Vstats = stats.filter { volunteers.contains(it.nivol) }
    val Vstats = stats.filter { it.nivol == "01000009046N" }

    println(Vstats.sumBy { it.activities.sumBy { activity -> activity.beginDate.until(activity.endDate, ChronoUnit.HOURS).toInt() } })

}