package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.infrastructure.TrainingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VolunteerTrainingController(val trainingService: TrainingService) {

    @GetMapping("/volunteer/trainings")
    fun volunteerTrainings(): String {
        trainingService.connect("", "")
        return mapper.writeValueAsString(trainingService.getAllVolunteerTrainings())
    }

}