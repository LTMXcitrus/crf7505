package com.lemonfactory.crf7505.domain.model.mission

import java.time.LocalDateTime

data class Mission(val beginDate: LocalDateTime,
                   val endDate: LocalDateTime,
                   val name: String,
                   val ul: String,
                   val inscriptions: List<Inscription>,
                   val roles: List<Role>) {

    fun getMissionInscription(): List<Inscription> {
        if (inscriptions.isEmpty()) {
            return roles.map { role -> Inscription(beginDate, endDate, role.type) }
        }
        return matchInscriptionsAndRoles()
    }

    private fun matchInscriptionsAndRoles(): List<Inscription> {
        val stillNeededInscriptions: MutableList<Inscription> = buildWantedInscriptions()

        inscriptions.forEach { inscription ->
            if(stillNeededInscriptions.contains(inscription)){
                stillNeededInscriptions.remove(inscription)
            } else {
                val neededInscription = stillNeededInscriptions
                        .find { neededInscription -> inscription.roleType == neededInscription.roleType }

            }
        }
        return stillNeededInscriptions.toList()
    }

    private fun buildWantedInscriptions(): MutableList<Inscription> {
        return roles.flatMap { role ->
            MutableList(role.quantity) { Inscription(beginDate, endDate, role.type) }
        }.toMutableList()
    }
}