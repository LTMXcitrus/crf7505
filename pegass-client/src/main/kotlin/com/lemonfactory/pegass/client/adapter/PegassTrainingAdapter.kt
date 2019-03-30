package com.lemonfactory.pegass.client.adapter

import com.lemonfactory.crf7505.domain.model.Training
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.format.PegassVolunteerTraining
import java.time.LocalDate.now

class PegassTrainingAdapter() {

    fun buildVolunteer(volunteer: PegassVolunteer, pegassVolunteerTrainings: List<PegassVolunteerTraining>): Volunteer {
        val trainings = pegassVolunteerTrainings.map { pegassTraining -> buildTraining(pegassTraining) }
        return Volunteer(
                volunteer.id,
                volunteer.prenom ?: "prénom inconnu",
                volunteer.nom ?: "nom inconnu",
                trainings,
                trainings.all { training -> training.upToDate }
        )
    }

    private fun buildTraining(pegassVolunteerTraining: PegassVolunteerTraining): Training {
        val upToDate = pegassVolunteerTraining.dateRecyclage?.toLocalDate()?.isAfter(now()) ?: true
        return Training(
                pegassVolunteerTraining.dateObtention.toLocalDate(),
                pegassVolunteerTraining.dateRecyclage?.toLocalDate(),
                pegassVolunteerTraining.formation.code,
                pegassVolunteerTraining.formation.libelle,
                upToDate
        )
    }

}