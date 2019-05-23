package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.MissionService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class MissionController(val missionService: MissionService) {

    @ModelAttribute
    fun initLocalDate(): LocalDate {
        return LocalDate.now()
    }


    @PostMapping("mission/activities")
    fun activities(@RequestBody user: PegassUser,
                   @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam start: LocalDate,
                   @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam end: LocalDate): String {
        return mapper.writeValueAsString(missionService.getAllMissions(user, start, end))
    }

}