package com.lemonfactory.covid

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun fromCsvLine(line: List<String>): ModelFromCSV {
    return ModelFromCSV(
            creationDate = LocalDateTime.parse(line[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
            email = line[1],
            phone = line[2],
            firstname = line[3],
            lastname = line[4],
            ul = line[5],
            vulnerable = line[6],
            roleInUl = if(line[7] == "Oui") line[8] else "",
            competences = line[9].split(",").map { it.trim() },
            profession = line[10],
            uniformeStatus = line[11],
            creneau00 = line[12].split(",").map { it.trim() },
            creneau02 = line[13].split(",").map { it.trim() },
            creneau04 = line[14].split(",").map { it.trim() },
            creneau06 = line[15].split(",").map { it.trim() },
            creneau08 = line[16].split(",").map { it.trim() },
            creneau10 = line[17].split(",").map { it.trim() },
            creneau12 = line[18].split(",").map { it.trim() },
            creneau14 = line[19].split(",").map { it.trim() },
            creneau16 = line[20].split(",").map { it.trim() },
            creneau18 = line[21].split(",").map { it.trim() },
            creneau20 = line[22].split(",").map { it.trim() },
            creneau22 = line[23].split(",").map { it.trim() }
    )
}

data class ModelFromCSV(
        val creationDate: LocalDateTime,
        val email: String,
        val phone: String,
        val firstname: String,
        val lastname: String,
        val ul: String,
        val vulnerable: String,
        val roleInUl: String,
        val competences: List<String>,
        val profession: String,
        val uniformeStatus: String,
        val creneau00: List<String>,
        val creneau02: List<String>,
        val creneau04: List<String>,
        val creneau06: List<String>,
        val creneau08: List<String>,
        val creneau10: List<String>,
        val creneau12: List<String>,
        val creneau14: List<String>,
        val creneau16: List<String>,
        val creneau18: List<String>,
        val creneau20: List<String>,
        val creneau22: List<String>
)