package com.lemonfactory.pegass.client.testutils

import com.lemonfactory.pegass.client.api.PegassVolunteer
import com.lemonfactory.pegass.client.api.activity.*
import java.time.LocalDateTime

fun aPegassActivity(): PegassActivity {
    return PegassActivity(
            "id",
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
                "id",
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
            "id",
            anActivity(),
            anActionGroup(),
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(8),
            "adresse",
            emptyList()
    )
}

fun anActivity(): Activity {
    return Activity("id", "type")
}