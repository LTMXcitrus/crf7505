package com.lemonfactory.crf7505.stats

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.config.ConfigKeys
import com.lemonfactory.crf7505.infrastructure.*
import com.lemonfactory.crf7505.model.CrfMail
import com.lemonfactory.crf7505.model.stats.api.Stat
import com.lemonfactory.crf7505.model.stats.bdd.DbVolunteerStats
import com.lemonfactory.crf7505.model.stats.bdd.VolunteerStats
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class StatsRepository(
        private val statsService: StatsService,
        private val statsDB: ObjectifyDAO<DbVolunteerStats>,
        private val volunteerRepository: VolunteerRepository,
        private val mailService: MailService,
        private val config: Config,
        private val connectedUserResolver: ConnectedUserResolver
) {

    fun retrieveStatsFor(nivol: String): Stat? {
        val volunteerStats = statsDB.findById<DbVolunteerStats>(nivol)?.toModel()
        return Stat(
                nivol = nivol,
                activities = volunteerStats?.activities ?: emptyList()
        )
    }

    fun loadStats(username: String, password: String, email: String, beginDate: LocalDate, endDate: LocalDate) {
        GlobalScope.launch {
            statsDB.findAll<DbVolunteerStats>().map { statsDB.delete(it) }
            val statsQuery = StatsQuery(
                    username,
                    password,
                    beginDate,
                    endDate,
                    volunteerRepository.retrieveAllVolunteers().map { it.nivol }.filter { it.isNotBlank() }
            )
            statsService.loadStats(statsQuery).forEach { stat -> statsDB.save(stat.toDbOject()) }
            sendLoadingCompleteMail(email)
        }
    }

    private fun sendLoadingCompleteMail(email: String) {

        mailService.sendMails(
                listOf(CrfMail(
                        config.getEnvRequired(ConfigKeys.RECAP_SENDER),
                        email,
                        "Chargement terminé",
                        "Le chargement des statistiques est terminé."
                ))
        )
    }

}