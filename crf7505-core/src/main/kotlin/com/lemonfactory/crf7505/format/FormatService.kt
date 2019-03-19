package com.lemonfactory.crf7505.format

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.TrainingService

class FormatService(val trainingService: TrainingService) {

    fun getAllVolunteers(): List<Volunteer> {
        return trainingService.getAllVolunteerTrainings()
    }

}