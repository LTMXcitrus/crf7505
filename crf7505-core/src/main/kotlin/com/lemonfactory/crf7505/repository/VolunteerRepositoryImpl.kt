package com.lemonfactory.crf7505.repository

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.VolunteerRepository

class VolunteerRepositoryImpl(private val volunteerDao: ObjectifyDAO<Volunteer>) : VolunteerRepository {

    override fun removeVolunteer(volunteer: Volunteer): Boolean {
        return volunteerDao.delete(volunteer)
    }

    override fun saveVolunteer(volunteer: Volunteer): Volunteer {
        volunteerDao.save(volunteer)
        return volunteer
    }

    override fun retrieveAllVolunteers(): List<Volunteer> {
        return volunteerDao.findAll()
    }

}