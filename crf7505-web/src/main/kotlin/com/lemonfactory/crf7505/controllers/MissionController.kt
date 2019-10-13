package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import com.lemonfactory.crf7505.mails.MailPreparator
import com.lemonfactory.crf7505.infrastructure.MailService
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.mails.MailHandler
import com.lemonfactory.crf7505.mapper
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
class MissionController(val missionRepository: MissionRepository, val mailService: MailService, val mailHandler: MailHandler) {

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

    @PostMapping("mission/recapMissions")
    fun recapMissions(@RequestBody missionsDay: List<MissionsDay>): String {
        return mapper.writeValueAsString(mailHandler.genMails(missionsDay))
    }

    @PostMapping("mission/sendRecap")
    fun sendRecap(@RequestBody mails: List<CrfMail>): String {
        mailService.sendMails(mails)
        return mapper.writeValueAsString(true)
    }

}