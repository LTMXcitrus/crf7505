package com.lemonfactory.crf7505.domain.model.mission

enum class ActivityGroup {

    US,
    AS,
    FORMATION,
    SOUTIEN_ACTIVITES

}


fun retrieveActivityGroupFromId(id: Int): ActivityGroup {
    return when(id) {
        2 -> ActivityGroup.AS
        17 -> ActivityGroup.FORMATION
        3 -> ActivityGroup.SOUTIEN_ACTIVITES
        else -> ActivityGroup.US
    }
}