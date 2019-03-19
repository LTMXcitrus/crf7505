package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.Training
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.format.PegassVolunteerTraining

class PegassTrainingAdapter() {

    fun buildVolunteer(volunteer: PegassVolunteer, pegassVolunteerTrainings: List<PegassVolunteerTraining>): Volunteer {
        val trainings = pegassVolunteerTrainings.map { pegassTraining -> buildTraining(pegassTraining) }
        return Volunteer(
                volunteer.id,
                volunteer.prenom ?: "prénom inconnu",
                volunteer.nom ?: "nom inconnu",
                trainings
        )
    }

    private fun buildTraining(pegassVolunteerTraining: PegassVolunteerTraining): Training {
        return Training(
                pegassVolunteerTraining.dateObtention.toLocalDate(),
                pegassVolunteerTraining.dateRecyclage?.toLocalDate(),
                pegassVolunteerTraining.formation.code,
                pegassVolunteerTraining.formation.libelle
        )
    }

}