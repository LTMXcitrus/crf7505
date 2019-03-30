package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.MissionService
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class MissionController(val missionService: MissionService) {

    @ModelAttribute
    fun initLocalDate(): LocalDate {
        return LocalDate.now()
    }


    @PostMapping("mission/activities")
    fun activities(@RequestBody user: PegassUser, @ModelAttribute start: LocalDate, @ModelAttribute end: LocalDate): String {
        return mapper.writeValueAsString(missionService.getAllMissions(user, start, end))
    }

}