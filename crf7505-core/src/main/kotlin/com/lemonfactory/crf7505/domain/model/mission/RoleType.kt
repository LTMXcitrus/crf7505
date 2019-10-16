package com.lemonfactory.crf7505.domain.model.mission

enum class RoleType(val code: String, val type: String, val baseContactRoleId: String = "", val toString: String = "") {

    PSC1("171", "FORM", "S", toString="PSC1"),
    PSE1("166", "FORM", "SEC", toString="PSE1"),
    PSE2("167", "FORM", "ES", toString="PSE2"),
    PSE1_CYCLISTE("12", "FORM", toString="PSE1 Cycliste"),
    PSE2_CYCLISTE("60", "FORM", toString="PSE2 Cycliste"),
    PSE1_CAVALIER("50", "FORM", toString="PSE1 Cavalier"),
    PSE2_CAVALIER("49", "FORM", toString="PSE2 Cavalier"),
    CI("75", "NOMI", "CI", toString="CI"),
    CDP("", "", "CP", toString="Chef de poste"),
    CHEF_SECTEUR("108", "NOMI", toString="Chef de Secteur"),
    CDPMGE("167", "NOMI", toString="Chef de Poste petite/moyenne envergure"),
    CI_BSPP("255", "NOMI", toString="CI BSPP"),
    CI_ALPHA("254", "NOMI", toString="CI ALPHA"),
    CH_VPSP("10", "COMP", toString="Chauffeur VPSP"),
    CH_VL("9", "COMP", toString="Chauffeur VL"),
    CH_QUAD("77", "COMP", toString="Chauffeur QUAD"),
    INFIRMIER("58", "COMP", toString="Infirmier"),
    MEDECIN("59", "COMP", toString="Médecin"),
    DLUS("40", "NOMI", toString="DLUS"),
    REGULATEUR("18", "COMP", toString="Régulateur"),
    REGULATEUR_TERRAIN("44", "COMP", toString="Régulateur Terrain"),
    AIDE_REGULATEUR("80", "COMP", toString="Aide régulateur"),
    LOGISTICIEN("", "", "L", toString="Logisticien"),
    ONYX("7", "COMP", toString="ONYX"),
    PARTICIPANT("0", "COMP", toString="Participant"),
    PARTICIPANT_EXT("1", "COMP", toString="Participant extérieur");
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
