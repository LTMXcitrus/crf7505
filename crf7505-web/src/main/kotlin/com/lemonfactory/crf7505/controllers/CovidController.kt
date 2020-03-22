package com.lemonfactory.crf7505.controllers

import com.lemonfactory.covid.DispoCovidService
import com.lemonfactory.covid.DispoSearch
import com.lemonfactory.crf7505.jackson.mapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CovidController(
        private val dispoCovidService: DispoCovidService
) {

    @GetMapping("covid/dispos")
    fun getDispos(): String {
        return mapper.writeValueAsString(dispoCovidService.retrieveDispos())
    }

    @GetMapping("covid/dispos/refresh")
    fun refreshDispos(): String {
        return mapper.writeValueAsString(dispoCovidService.refresh())
    }

    @PostMapping("covid/dispos")
    fun searchDispos(@RequestBody search: DispoSearch): String {
        return mapper.writeValueAsString(dispoCovidService.search(search))
    }
}