package com.lemonfactory.crf7505.infrastructure

import com.lemonfactory.crf7505.domain.model.Volunteer

interface VolunteerRepository {

    fun retrieveAllVolunteers(): List<Volunteer>

    fun saveVolunteer(volunteer: Volunteer): Volunteer

}