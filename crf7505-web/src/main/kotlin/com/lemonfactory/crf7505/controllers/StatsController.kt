package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.jackson.mapper
import com.lemonfactory.crf7505.model.CrfMail
import com.lemonfactory.crf7505.stats.StatsRepository
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class StatsController(private val statsRepository: StatsRepository) {

    @PostMapping("stats/launch")
    fun launch(@RequestBody statsLaunchArgs: StatsLaunchArgs): String {
        with(statsLaunchArgs) {
            statsRepository.loadStats(username, password, email, beginDate, endDate)
        }
        return mapper.writeValueAsString(true)
    }

    @GetMapping("stats/get/{nivol}")
    fun get(@PathVariable("nivol") nivol: String): String {
        return mapper.writeValueAsString(statsRepository.retrieveStatsFor(nivol))
    }

}

data class StatsLaunchArgs(
        val username: String,
        val password: String,
        val email: String,
        val beginDate: LocalDate,
        val endDate: LocalDate
)
