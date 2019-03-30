package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.infrastructure.TrainingService
import com.lemonfactory.crf7505.security.user.ApplicationUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class VolunteerTrainingController(val trainingService: TrainingService) {

    @PostMapping("/volunteer/trainings")
    fun volunteerTrainings(@RequestBody user: PegassUser): String {
        return mapper.writeValueAsString(trainingService.getAllVolunteerTrainings(user.username, user.password))
    }

}