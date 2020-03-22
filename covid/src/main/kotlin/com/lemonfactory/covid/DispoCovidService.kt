package com.lemonfactory.covid

import java.time.LocalDateTime

class DispoCovidService(private val dispoImporter: DispoImporter) {

    private var dispos = DispoResponse()


    fun retrieveDispos(): DispoResponse {
        if (dispos.date == null) {
            dispos = DispoResponse(LocalDateTime.now(), dispoImporter.import())
        }
        return dispos
    }

    fun refresh(): DispoResponse {
        dispos = DispoResponse(LocalDateTime.now(), dispoImporter.import())
        return dispos
    }

    fun search(search: DispoSearch): DispoResponse {
        val currentResponse = retrieveDispos()
        val dispos = currentResponse.dispos
        return currentResponse.copy(
                dispos = dispos.filter { dispo -> search.firstname?.let { dispo.firstname.toLowerCase().contains(it.toLowerCase()) } ?: true }
                        .filter { dispo -> search.lastname?.let { dispo.lastname.toLowerCase().contains(it.toLowerCase()) } ?: true }
                        .filter { dispo -> search.competences?.any { dispo.competences.contains(it) } ?: true }
                        .filter { dispo -> search.ul?.let { dispo.ul.contains(it) } ?: true }
                        .filter { dispo -> search.creneau?.let { dispo.hasMatchingHours(it.toCreneau())} ?: true}
        )
    }

    private fun Dispo.hasMatchingHours(searchCreneau: Creneau): Boolean {
        return this.creneaux.any {
            (it.start.isBefore(searchCreneau.start) || it.start.isEqual(searchCreneau.start))
                    && (it.end.isAfter(searchCreneau.end) || it.end.isEqual(searchCreneau.end))
        }
    }

}

data class DispoResponse(
        val date: LocalDateTime? = null,
        val dispos: List<Dispo> = emptyList()
)