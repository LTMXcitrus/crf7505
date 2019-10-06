package com.lemonfactory.crf7505.domain.model.mission

enum class RoleType(val code: String, val type: String, val baseContactRoleId: String = "") {

    PSC1("171", "FORM", "S"),
    PSE1("166", "FORM", "SEC"),
    PSE2("167", "FORM", "ES"),
    PSE1_CYCLISTE("12", "FORM"),
    PSE2_CYCLISTE("60", "FORM"),
    PSE1_CAVALIER("50", "FORM"),
    PSE2_CAVALIER("49", "FORM"),
    CI("75", "NOMI", "CI"),
    CDP("", "", "CP"),
    CHEF_SECTEUR("108", "NOMI"),
    CDPMGE("167", "NOMI"),
    CI_BSPP("255", "NOMI"),
    CI_ALPHA("254", "NOMI"),
    CH_VPSP("10", "COMP"),
    CH_VL("9", "COMP"),
    CH_QUAD("77", "COMP"),
    INFIRMIER("58", "COMP"),
    MEDECIN("59", "COMP"),
    DLUS("40", "NOMI"),
    REGULATEUR("18", "COMP"),
    REGULATEUR_TERRAIN("44", "COMP"),
    AIDE_REGULATEUR("80", "COMP"),
    LOGISTICIEN("", "", "L"),
    ONYX("7", "COMP"),
    PARTICIPANT("0", "COMP"),
    PARTICIPANT_EXT("1", "COMP");
}

fun roleTypeFrom(type: String?, code: String): RoleType {
    return RoleType.values()
            .find { value -> matchRoleType(value, type, code) }
            ?: RoleType.PARTICIPANT
}

private fun matchRoleType(value: RoleType, type: String?, code: String): Boolean {
    if (type.isNullOrBlank()) {
        return value.baseContactRoleId == code
    }
    return value.code == code && value.type == type
}
