package com.lemonfactory.crf7505.domain.model.mission

enum class TypeActivity {

    MISSION,
    REUNION

}

fun typeActivityOf(id: Int): TypeActivity {
    return when(id) {
        21 -> TypeActivity.REUNION
        else -> TypeActivity.MISSION
    }
}
