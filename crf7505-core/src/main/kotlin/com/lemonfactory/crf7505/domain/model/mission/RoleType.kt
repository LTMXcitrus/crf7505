package com.lemonfactory.crf7505.domain.model.mission

enum class RoleType(val codes: List<String>, val types: List<String>, val baseContactRoleId: String = "", val toString: String = "") {

    PSC1(listOf("171"), listOf("FORM"), "S", toString = "PSC1"),
    PSE1(listOf("166"), listOf("FORM"), "SEC", toString = "PSE1"),
    PSE2(listOf("167"), listOf("FORM"), "ES", toString = "PSE2"),
    PSE1_CYCLISTE(listOf("12"), listOf("FORM"), toString = "PSE1 Cycliste"),
    PSE2_CYCLISTE(listOf("60"), listOf("FORM"), toString = "PSE2 Cycliste"),
    PSE1_CAVALIER(listOf("50"), listOf("FORM"), toString = "PSE1 Cavalier"),
    PSE2_CAVALIER(listOf("49"), listOf("FORM"), toString = "PSE2 Cavalier"),
    CI(listOf("75"), listOf("NOMI"), "CI", toString = "CI"),
    CDP(listOf(""), listOf(""), "CP", toString = "Chef de poste"),
    CHEF_SECTEUR(listOf("108"), listOf("NOMI"), toString = "Chef de Secteur"),
    CDPMGE(listOf("167"), listOf("NOMI"), toString = "Chef de Poste petite/moyenne envergure"),
    CI_BSPP(listOf("255"), listOf("NOMI"), toString = "CI BSPP"),
    CI_ALPHA(listOf("254"), listOf("NOMI"), toString = "CI ALPHA"),
    CH_VPSP(listOf("10", "362"), listOf("COMP", "NOMI"), toString = "Chauffeur VPSP"),
    CH_VL(listOf("9"), listOf("COMP"), toString = "Chauffeur VL"),
    CH_QUAD(listOf("77"), listOf("COMP"), toString = "Chauffeur QUAD"),
    INFIRMIER(listOf("58"), listOf("COMP"), toString = "Infirmier"),
    MEDECIN(listOf("59"), listOf("COMP"), toString = "Médecin"),
    DLUS(listOf("40"), listOf("NOMI"), toString = "DLUS"),
    REGULATEUR(listOf("18"), listOf("COMP"), toString = "Régulateur"),
    REGULATEUR_TERRAIN(listOf("44"), listOf("COMP"), toString = "Régulateur Terrain"),
    AIDE_REGULATEUR(listOf("80"), listOf("COMP"), toString = "Aide régulateur"),
    LOGISTICIEN(listOf(""), listOf(""), "L", toString = "Logisticien"),
    ONYX(listOf("7"), listOf("COMP"), toString = "ONYX"),
    PARTICIPANT(listOf("0"), listOf("COMP"), toString = "Participant"),
    PARTICIPANT_EXT(listOf("1"), listOf("COMP"), toString = "Participant extérieur"),
    FORMATEUR(listOf("FORMATEUR"), emptyList(), "FORMATEUR", toString = "Formateur"),
    ASSISTANT(listOf("ASSISTANT"), emptyList(), "ASSISTANT", toString = "Assistant"),
    TSA(listOf("32"), listOf("COMP"), "", toString = "TSA"),
    CADRE_LOCAL(listOf("6"), listOf("COMP"), "", toString = "Cadre Local");
}

fun roleTypeFrom(type: String?, code: String): RoleType {
    return RoleType.values()
            .find { role -> matchRoleType(role, type, code) }
            ?: RoleType.PARTICIPANT
}

private fun matchRoleType(roleType: RoleType, type: String?, code: String): Boolean {
    if (type.isNullOrBlank()) {
        return roleType.baseContactRoleId == code
    }
    return roleType.codes.contains(code) && roleType.types.contains(type)
}
