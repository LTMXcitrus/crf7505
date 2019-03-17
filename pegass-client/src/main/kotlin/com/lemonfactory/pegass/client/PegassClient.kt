package com.lemonfactory.pegass.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.lemonfactory.pegass.client.api.PegassActivity

class PegassClient(val pegassConnector: PegassConnector) {

    fun getActivities(): List<PegassActivity> {
        val pegassResponse = pegassConnector.getPage("https://pegass.croix-rouge.fr/crf/rest/activite?debut=2018-09-10&fin=2018-09-26&groupeAction=1&zoneGeoId=75&zoneGeoType=departement")
        return mapper.readValue(pegassResponse)
    }

}