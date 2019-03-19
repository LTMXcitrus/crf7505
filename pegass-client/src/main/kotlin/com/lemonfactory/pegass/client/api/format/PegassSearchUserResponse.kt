package com.lemonfactory.pegass.client.api.format

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.lemonfactory.pegass.client.api.PegassVolunteer

@JsonIgnoreProperties(ignoreUnknown = true)
data class PegassSearchUserResponse(val list: List<PegassVolunteer>)