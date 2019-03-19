package com.lemonfactory.pegass.client

import com.lemonfactory.pegass.client.adapter.PegassTrainingAdapter
import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.format.PegassVolunteerTraining

class PegassTrainingService(val pegassClient: PegassClient, val pegassTrainingAdapter: PegassTrainingAdapter) {


    fun getTrainings() {
        pegassClient.getVolunteers()
                .map { volunteer -> getVolunteerTraining(volunteer) }
    }

    fun getVolunteerTraining(volunteer: PegassVolunteer) {
        val pegassVolunteerTraining = pegassClient.getVolunteerTrainingState(volunteer.id)
        pegassTrainingAdapter.buildVolunteer(volunteer, pegassVolunteerTraining)
    }
}