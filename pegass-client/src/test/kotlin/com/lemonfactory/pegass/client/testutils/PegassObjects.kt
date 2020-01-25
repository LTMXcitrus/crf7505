package com.lemonfactory.pegass.client.testutils

import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.activity.*
import com.lemonfactory.pegass.client.api.activity.inscription.PegassInscription
import com.lemonfactory.pegass.client.api.activity.inscription.PegassUtilisateur
import java.time.LocalDateTime

fun aPegassActivity(): PegassActivity {
    return PegassActivity(
            "codes",
            "types",
            "libelle",
            anActivityType(),
            anActivityReccurrence(),
            "statut",
            structureParisV(),
            structureParisV(),
            structureParisV(),
            emptyList(),
            aPegassVolunteer(),
            "commentaire",
            true
    )
}

private fun structureParisV() = Structure(893)

private fun anActivityReccurrence() =
        ActivityReccurrence(
                "codes",
                false,
                "types",
                emptyList(),
                LocalDateTime.now(),
                LocalDateTime.now()
        )

private fun anActionGroup() = ActionGroup(
        "actionGroupLibelle",
        "tri",
        0)

private fun anActivityType() =
        ActivityType(
                0,
                "typeLibelle",
                anActionGroup(),
                anAction()
        )

private fun anAction() = Action(
        0,
        "actionLibelle",
        anActionGroup()
)

fun aPegassVolunteer(): PegassVolunteer {
    return PegassVolunteer(
            "volunteer",
            true,
            false,
            "name",
            "firstname"
    )
}

fun anActivitySeance(): ActivitySeance {
    return ActivitySeance(
            "seance-codes",
            anActivity(),
            anActionGroup(),
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(8),
            "adresse",
            emptyList()
    )
}

fun anActivityRole(): ActivityRole {
    return ActivityRole(
            "codes",
            "codes",
            "codes",
            true,
            1,
            "types",
            0
    )
}

fun anActivity(): Activity {
    return Activity("codes", "types")
}

fun aPegassInscription(): PegassInscription {
    return PegassInscription(
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(8),
            "codes",
            false,
            "167",
            "statut",
            "FORM",
            null,
            PegassUtilisateur(true, "id", false)
    )
}