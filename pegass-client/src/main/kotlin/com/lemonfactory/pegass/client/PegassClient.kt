package com.lemonfactory.pegass.client

import com.fasterxml.jackson.module.kotlin.readValue
import com.lemonfactory.crf7505.mapper
import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.lemonfactory.pegass.client.api.format.PegassSearchUserResponse
import com.lemonfactory.pegass.client.api.format.PegassVolunteerNomination
import com.lemonfactory.pegass.client.api.format.PegassVolunteerTraining
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val PEGASS_DATETIME_FORMATTER = "yyyy-MM-dd"

class PegassClient {
    private val LOGGER = LoggerFactory.getLogger(PegassClient::class.java)

    fun getActivitiesFilteredWithDates(pegassSession: PegassSession, start: LocalDate, end: LocalDate): List<PegassActivity> {
        return getActivities(pegassSession, start, end)
                .mapNotNull { pegassActivity -> filterByDate(pegassActivity, start, end) }
    }

    private fun filterByDate(pegassActivity: PegassActivity, start: LocalDate, end: LocalDate): PegassActivity? {
        val seanceList = pegassActivity.seanceList
                .filter { seance -> seance.fin.toLocalDate().isAfter(start) && seance.debut.toLocalDate().isBefore(end) }
        if(seanceList.isEmpty()) {
            return null
        }
        return pegassActivity.copy(seanceList = seanceList)
    }

    fun getActivities(pegassSession: PegassSession, start: LocalDate, end: LocalDate): List<PegassActivity> {
        val startDate = start.format(DateTimeFormatter.ofPattern(PEGASS_DATETIME_FORMATTER))
        val endDate = end.format(DateTimeFormatter.ofPattern(PEGASS_DATETIME_FORMATTER))

        val url = "https://pegass.croix-rouge.fr/crf/rest/activite?debut=$startDate&fin=$endDate&groupeAction=1&zoneGeoId=75&zoneGeoType=departement"
        LOGGER.info("Retrieve activities: $url")

        return mapper.readValue(pegassSession.getPage(url))
    }

    fun getVolunteers(pegassSession: PegassSession): List<PegassVolunteer> {
        val pegassResponse = pegassSession
                .getPage("https://pegass.croix-rouge.fr/crf/rest/utilisateur?groupeAction=1&page=0&pageInfo=true&searchType=benevoles&structure=893&withMoyensCom=true")
        return mapper.readValue<PegassSearchUserResponse>(pegassResponse).list
    }

    fun getVolunteerTrainingState(pegassSession: PegassSession, nivol: String): List<PegassVolunteerTraining> {
        val pegassResponse = pegassSession
                .getPage("https://pegass.croix-rouge.fr/crf/rest/formationutilisateur?utilisateur=$nivol")
        return mapper.readValue(pegassResponse)
    }

    fun getVolunteerNominations(pegassSession: PegassSession, nivol: String): List<PegassVolunteerNomination> {
        val pegassResponse = pegassSession
                .getPage("https://pegass.croix-rouge.fr/crf/rest/nominationutilisateur?utilisateur=$nivol")
        return mapper.readValue(pegassResponse)
    }

}