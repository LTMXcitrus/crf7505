package com.lemonfactory.crf7505.model.stats.api

import com.lemonfactory.crf7505.model.mission.Mission
import com.lemonfactory.crf7505.model.mission.TypeActivity

data class Stat(val nivol: String,
                val firstname: String = "",
                val lastname: String = "",
                val activities: List<Mission>) {


    val reseau: Int
        get() = activities.count { it.activityType == TypeActivity.RESEAU }

    val dps: Int
        get() = activities.count { it.activityType == TypeActivity.POSTE_SECOURS }


}