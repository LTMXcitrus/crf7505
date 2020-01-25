package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.model.stats.bdd.VolunteerStats
import java.time.LocalDate


data class StatsQuery(
        val username: String,
        val password: String,
        val beginDate: LocalDate,
        val endDate: LocalDate,
        val nivols: List<String>
)

interface StatsService {

    fun loadStats(statsQuery: StatsQuery): List<VolunteerStats>

}