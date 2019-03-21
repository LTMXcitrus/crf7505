package com.lemonfactory.pegass.client

import com.fasterxml.jackson.module.kotlin.readValue
import com.lemonfactory.crf7505.mapper
import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.lemonfactory.pegass.client.api.format.PegassSearchUserResponse
import com.lemonfactory.pegass.client.api.format.PegassVolunteerNomination
import com.lemonfactory.pegass.client.api.format.PegassVolunteerTraining

class PegassClient(val pegassConnector: PegassConnector) {

    fun getActivities(username: String, password: String): List<PegassActivity> {
        val pegassResponse = pegassConnector.getPage(username, password, "https://pegass.croix-rouge.fr/crf/rest/activite?debut=2018-09-10&fin=2018-09-26&groupeAction=1&zoneGeoId=75&zoneGeoType=departement")
        return mapper.readValue(pegassResponse)
    }

    fun getVolunteers(username: String, password: String): List<PegassVolunteer> {
        val pegassResponse = pegassConnector.getPage(username, password, "https://pegass.croix-rouge.fr/crf/rest/utilisateur?groupeAction=1&page=0&pageInfo=true&perPage=11&searchType=benevoles&structure=893&withMoyensCom=true")
        return mapper.readValue<PegassSearchUserResponse>(pegassResponse).list
    }

    fun getVolunteerTrainingState(username: String, password: String, nivol: String): List<PegassVolunteerTraining> {
        val pegassResponse = pegassConnector.getPage(username, password, "https://pegass.croix-rouge.fr/crf/rest/formationutilisateur?utilisateur=$nivol")
        return mapper.readValue(pegassResponse)
    }

    fun getVolunteerNominations(username: String, password: String, nivol: String): List<PegassVolunteerNomination> {
        val pegassResponse = pegassConnector.getPage(username, password, "https://pegass.croix-rouge.fr/crf/rest/nominationutilisateur?utilisateur=$nivol")
        return mapper.readValue(pegassResponse)
    }

}