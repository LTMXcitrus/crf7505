package com.lemonfactory.crf7505.domain.model

data class VolunteerTraining(val id: String,
                             val firstname: String,
                             val lastname: String,
                             val trainings: List<Training>,
                             val upToDate: Boolean)