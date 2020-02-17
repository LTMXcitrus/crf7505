package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.MailService
import com.lemonfactory.crf7505.infrastructure.MissionRepository
import com.lemonfactory.crf7505.jackson.mapper
import com.lemonfactory.crf7505.mails.MailHandler
import com.lemonfactory.pegass.client.PegassApiService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

data class PegassApiRequest(
        val user: PegassUser,
        val url: String
)

@RestController
class ApiController(val pegassApiService: PegassApiService) {

    @PostMapping("pegass/api")
    fun apiResponse(@RequestBody request: PegassApiRequest): String {
        return mapper.writeValueAsString(pegassApiService.callPegassApi(request.user, request.url))
    }
}
