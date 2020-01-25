package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.api.GenerateMailBody
import com.lemonfactory.crf7505.model.CrfMail
import com.lemonfactory.crf7505.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.MailService
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.mails.MailHandler
import com.lemonfactory.crf7505.jackson.mapper
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
                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam end: LocalDateTime,
                   @RequestParam addedDaysForLocalMissions: Number): String {
        return mapper.writeValueAsString(missionRepository.getActivities(user, start, end, addedDaysForLocalMissions))
    }

    @PostMapping("mission/recapMissions")
    fun recapMissions(@RequestBody body: GenerateMailBody): String {
        return mapper.writeValueAsString(mailHandler.genMails(body.subject, body.header, body.activities, body.footer, body.respMission))
    }

    @PostMapping("mission/sendRecap")
    fun sendRecap(@RequestBody mails: List<CrfMail>): String {
        return mapper.writeValueAsString(mailService.sendMails(mails))
    }

}
