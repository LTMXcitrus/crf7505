package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.infrastructure.MissionService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
class MissionController(val missionRepository: MissionRepository) {

    @ModelAttribute
    fun initLocalDate(): LocalDate {
        return LocalDate.now()
    }


    @PostMapping("mission/activities")
    fun activities(@RequestBody user: PegassUser,
                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam start: LocalDateTime,
                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam end: LocalDateTime): String {
        return mapper.writeValueAsString(missionRepository.getMissionsByDay(user, start, end))
    }

}