package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.TrainingService
import com.lemonfactory.pegass.client.adapter.PegassTrainingAdapter
import com.lemonfactory.pegass.client.api.PegassVolunteer

class PegassTrainingService(val pegassClient: PegassClient, val pegassTrainingAdapter: PegassTrainingAdapter) : TrainingService {


    override fun getAllVolunteerTrainings(username: String, password: String): List<Volunteer> {
        val pegassConnector = PegassSession(username, password)
        return pegassClient.getVolunteers(pegassConnector)
                .map { volunteer -> getVolunteerTraining(pegassConnector, volunteer) }
    }

    private fun getVolunteerTraining(pegassConnector: PegassSession, volunteer: PegassVolunteer): Volunteer {
        val pegassVolunteerTraining = pegassClient.getVolunteerTrainingState(pegassConnector, volunteer.id)
        return pegassTrainingAdapter.buildVolunteer(volunteer, pegassVolunteerTraining)
    }
}