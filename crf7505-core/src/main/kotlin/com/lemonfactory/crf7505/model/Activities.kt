package com.lemonfactory.crf7505.model

import com.lemonfactory.crf7505.model.mission.Mission

data class Activities(val localActivities: List<Mission>,
                      val externalActivities: List<Mission>,
                      val localStructure: String?)