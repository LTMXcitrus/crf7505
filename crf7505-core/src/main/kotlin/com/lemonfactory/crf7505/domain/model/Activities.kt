package com.lemonfactory.crf7505.domain.model

import com.lemonfactory.crf7505.domain.model.mission.Mission

data class Activities(val localActivities: List<Mission>,
                      val externalActivities: List<Mission>,
                      val localStructure: String?)