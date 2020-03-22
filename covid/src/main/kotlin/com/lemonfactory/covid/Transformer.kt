package com.lemonfactory.covid

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Transformer {

    fun transform(csv: ModelFromCSV): Dispo {
        val creneaux = buildCreneaux(csv)

        return Dispo(
                email = csv.email,
                phone = csv.phone,
                firstname = csv.firstname,
                lastname = csv.lastname,
                ul = csv.ul,
                vulnerable = transformVulnerable(csv.vulnerable),
                roleInUl = csv.roleInUl,
                competences = csv.competences,
                profession = csv.profession,
                uniformeStatus = transformUniformeStatus(csv.uniformeStatus),
                creneaux = creneaux,
                creneauxRepresentations = creneaux.transform()
        )
    }

    private fun transformVulnerable(vulnerable: String): String {
        if(vulnerable == "Je ne fais pas partie des personnes vulnérables") {
          return "Non"
        }
        return "Oui"
    }

    private fun transformUniformeStatus(uniformeStatus: String): String {
        if(uniformeStatus == "J'ai une dotation") {
            return "Oui"
        }
        return "Non"
    }

    private fun List<Creneau>.transform(): List<String> {
        return this.map { it.transform() }

    }

    private fun Creneau.transform(): String {
        if(start.hour == 0 && end.hour == 0) {
            return start.format(DateTimeFormatter.ofPattern("eeee dd"))
        }
        if(end.hour == 0){
            return "${start.format(DateTimeFormatter.ofPattern("eeee dd HH'h'mm"))}-23h59"
        }
        return "${start.format(DateTimeFormatter.ofPattern("eeee dd HH'h'mm"))}-${end.format(DateTimeFormatter.ofPattern("HH'h'mm"))}"
    }

    private fun buildCreneaux(csv: ModelFromCSV): List<Creneau> {
        val allCreneaux = listOf(
                csv.creneau00,
                csv.creneau02,
                csv.creneau04,
                csv.creneau06,
                csv.creneau08,
                csv.creneau10,
                csv.creneau12,
                csv.creneau14,
                csv.creneau16,
                csv.creneau18,
                csv.creneau20,
                csv.creneau22
        )
                .mapIndexed { index, days ->
                    days.mapNotNull { buildCreneau(it, index * 2) }
                }
                .flatten()

        val byDay = allCreneaux.groupBy { it.start.dayOfWeek }
        val pair = byDay.mapValues { it.value.concatSameDayCreneau() }
        return pair.values.flatten()
    }

    private fun buildCreneau(date: String, startHour: Int): Creneau? {
        if (date.trim().isEmpty()) {
            return null
        }
        val localDate = LocalDate.parse("$date Mars 2020".toLowerCase(), DateTimeFormatter.ofPattern("eeee dd MMMM yyyy"))
        val endHour = if (startHour + 2 < 24) startHour + 2 else 0
        val endTime = if(endHour == 0) localDate.atTime(endHour, 0).plusDays(1) else localDate.atTime(endHour, 0)
        return Creneau(
                localDate.atTime(startHour, 0),
                endTime
        )
    }

    private fun List<Creneau>.concatSameDayCreneau(): List<Creneau> {
        if (this.isEmpty()) {
            return emptyList()
        }
        val resultList = mutableListOf<Creneau>()
        var current = this[0]
        this.forEachIndexed { index, _ ->
            if (index + 1 < this.size) {
                val nextCreneau = this[index + 1]
                current = if (current.end.isEqual(nextCreneau.start)) {
                    Creneau(current.start, nextCreneau.end)
                } else {
                    resultList.add(current)
                    nextCreneau
                }
            } else {
                resultList.add(current)
            }
        }
        return resultList
    }

}