package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.TrainingService
import com.lemonfactory.pegass.client.adapter.PegassTrainingAdapter
import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.format.PegassVolunteerTraining

class PegassTrainingService(val pegassClient: PegassClient, val pegassTrainingAdapter: PegassTrainingAdapter) : TrainingService {

    override fun connect(username: String, password: String) {
        pegassClient.connect(username, password)
    }

    override fun getAllVolunteerTrainings(): List<Volunteer> {
        return pegassClient.getVolunteers()
                .map { volunteer -> getVolunteerTraining(volunteer) }
    }

    private fun getVolunteerTraining(volunteer: PegassVolunteer): Volunteer {
        val pegassVolunteerTraining = pegassClient.getVolunteerTrainingState(volunteer.id)
        return pegassTrainingAdapter.buildVolunteer(volunteer, pegassVolunteerTraining)
    }
}