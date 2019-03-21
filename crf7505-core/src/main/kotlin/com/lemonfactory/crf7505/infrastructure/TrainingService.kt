package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.Volunteer

interface TrainingService {
    fun getAllVolunteerTrainings(username: String, password: String): List<Volunteer>
}