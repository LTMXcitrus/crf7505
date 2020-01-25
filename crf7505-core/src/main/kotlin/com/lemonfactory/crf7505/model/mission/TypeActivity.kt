package com.lemonfactory.crf7505.model.mission

enum class TypeActivity {

    MISSION,
    REUNION,
    RESEAU,
    POSTE_SECOURS

}

fun typeActivityOf(id: Int): TypeActivity {
    return when(id) {
        21 -> TypeActivity.REUNION
        65 -> TypeActivity.RESEAU
        19 -> TypeActivity.POSTE_SECOURS
        else -> TypeActivity.MISSION
    }
}
