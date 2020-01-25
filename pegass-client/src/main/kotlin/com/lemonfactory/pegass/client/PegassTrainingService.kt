package com.lemonfactory.pegass.client

import com.lemonfactory.crf7505.model.VolunteerTraining
import com.lemonfactory.crf7505.infrastructure.TrainingService
import com.lemonfactory.pegass.client.adapter.PegassTrainingAdapter
import com.lemonfactory.pegass.client.api.PegassVolunteer

class PegassTrainingService(val pegassClient: PegassClient, val pegassTrainingAdapter: PegassTrainingAdapter) : TrainingService {


    override fun getAllVolunteerTrainings(username: String, password: String): List<VolunteerTraining> {
        val pegassConnector = PegassSession(username, password)
        return pegassClient.getVolunteers(pegassConnector)
                .map { volunteer -> getVolunteerTraining(pegassConnector, volunteer) }
    }

    private fun getVolunteerTraining(pegassConnector: PegassSession, volunteer: PegassVolunteer): VolunteerTraining {
        val pegassVolunteerTraining = pegassClient.getVolunteerTrainingState(pegassConnector, volunteer.id)
        return pegassTrainingAdapter.buildVolunteer(volunteer, pegassVolunteerTraining)
    }
}