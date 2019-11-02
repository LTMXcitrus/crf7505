package com.lemonfactory.pegass.client.api.activity

import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.activity.inscription.PegassInscription

data class PegassActivity(val id: String,
                          val type: String,
                          val libelle: String,
                          val typeActivite: ActivityType,
                          val recurrence: ActivityReccurrence?,
                          val statut: String,
                          val structureOrganisatrice: Structure,
                          val structureMenantActivite: Structure,
                          val structureCreateur: Structure?,
                          val seanceList: List<ActivitySeance> = emptyList(),
                          val responsable: PegassVolunteer,
                          val commentaire: String?,
                          val rappel: Boolean)