package com.lemonfactory.pegass.client.testutils

import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.activity.*
import com.lemonfactory.pegass.client.api.activity.inscription.PegassInscription
import java.time.LocalDateTime

fun aPegassActivity(): PegassActivity {
    return PegassActivity(
            "code",
            "type",
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
                "code",
                false,
                "type",
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
            "seance-code",
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
            "code",
            "code",
            "code",
            true,
            1,
            "type",
            0
    )
}

fun anActivity(): Activity {
    return Activity("code", "type")
}

fun aPegassInscription(): PegassInscription {
    return PegassInscription(
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(8),
            "code",
            false,
            "167",
            "statut",
            "FORM"
    )
}