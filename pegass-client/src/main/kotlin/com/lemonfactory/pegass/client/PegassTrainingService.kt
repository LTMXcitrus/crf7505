package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.TrainingService
import com.lemonfactory.pegass.client.adapter.PegassTrainingAdapter
import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.format.PegassVolunteerTraining

class PegassTrainingService(val pegassClient: PegassClient, val pegassTrainingAdapter: PegassTrainingAdapter) : TrainingService {


    override fun getAllVolunteerTrainings(username: String, password: String ): List<Volunteer> {
        return pegassClient.getVolunteers(username, password)
                .map { volunteer -> getVolunteerTraining(username, password, volunteer) }
    }

    private fun getVolunteerTraining(username: String, password: String, volunteer: PegassVolunteer): Volunteer {
        val pegassVolunteerTraining = pegassClient.getVolunteerTrainingState(username, password, volunteer.id)
        return pegassTrainingAdapter.buildVolunteer(volunteer, pegassVolunteerTraining)
    }
}