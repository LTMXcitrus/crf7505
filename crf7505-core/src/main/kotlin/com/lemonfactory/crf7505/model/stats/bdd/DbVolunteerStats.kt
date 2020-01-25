package com.lemonfactory.crf7505.model.stats.bdd

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.lemonfactory.crf7505.model.ObjectifyElement
import com.lemonfactory.crf7505.model.mission.DbMission
import com.lemonfactory.crf7505.model.mission.Mission

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class DbVolunteerStats(@Id var nivol: String = "",
                            var activities: List<DbMission> = mutableListOf()
) : ObjectifyElement {
    override fun getId(): String {
        return nivol
    }

    fun toModel(): VolunteerStats {
        return VolunteerStats(
                nivol,
                activities.map { it.toModel() }
        )
    }
}

data class VolunteerStats(
        val nivol: String,
        val activities: List<Mission>
) {
    fun toDbOject(): DbVolunteerStats {
        return DbVolunteerStats(
                nivol,
                activities.map { it.toDbObject() }
        )
    }
}