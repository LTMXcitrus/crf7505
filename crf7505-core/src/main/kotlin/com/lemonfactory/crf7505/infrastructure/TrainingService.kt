package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.Volunteer

interface TrainingService {
    fun getAllVolunteerTrainings(): List<Volunteer>
    fun connect(username: String, password: String)
}