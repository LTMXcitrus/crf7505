package com.lemonfactory.crf7505.domain.model.mission

fun roleTypeFrom(id: String, type: String): RoleType {
    return RoleType.values()
            .find { value -> value.id == id && value.type == type }
            ?: RoleType.PARTICIPANT
}

enum class RoleType(val id: String, val type: String) {
    PSC1("171", "FORM"),
    PSE1("166", "FORM"),
    PSE2("167", "FORM"),
    PSE1_CYCLISTE("12", "FORM"),
    PSE2_CYCLISTE("60", "FORM"),
    PSE1_CAVALIER("50", "FORM"),
    PSE2_CAVALIER("49", "FORM"),
    CI("75", "NOMI"),
    CHEF_SECTEUR("108", "NOMI"),
    CDPMGE("167", "NOMI"),
    CH_VPSP("10", "COMP"),
    CH_VL("9", "COMP"),
    CH_QUAD("77", "COMP"),
    INFIRMIER("58", "COMP"),
    MEDECIN("59", "COMP"),
    DLUS("40", "NOMI"),
    REGULATEUR("18", "COMP"),
    REGULATEUR_TERRAIN("44", "COMP"),
    AIDE_REGULATEUR("80", "COMP"),
    PARTICIPANT("0", "COMP");

}