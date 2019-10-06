package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.VolunteerTraining

interface TrainingService {
    fun getAllVolunteerTrainings(username: String, password: String): List<VolunteerTraining>
}