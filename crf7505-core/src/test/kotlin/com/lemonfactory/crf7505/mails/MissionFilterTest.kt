package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.RoleType.*
import com.lemonfactory.crf7505.utils.Missions.aLocalReunion
import com.lemonfactory.crf7505.utils.Missions.aMission
import com.lemonfactory.crf7505.utils.Missions.aMissionsWithMissingRolesFor
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MissionFilterTest {

    private val filter = MissionFilter()


    @Test
    fun `filter for PSE2 only should return only PSE2 missions`() {
        // Given
        val mission1 = aMissionsWithMissingRolesFor(listOf(PSE1, PSE2))
        val mission2 = aMissionsWithMissingRolesFor(listOf(PSE1))
        val missions = listOf(mission1, mission2)

        val volunteer = Volunteer(role = PSE2, interestedIn = listOf(PSE2.name))


        // When
        val missionsFiltered = filter.filter(missions, volunteer, "")

        // Then

        assertThat(missionsFiltered).containsExactly(mission1)
    }

    @Test
    fun `filter with PSE2 volunteer without interest should return all mission below PSE2 included`() {
        // Given
        val mission1 = aMissionsWithMissingRolesFor(listOf(PSE1, PSE2))
        val mission2 = aMissionsWithMissingRolesFor(listOf(PSE1))
        val missions = listOf(mission1, mission2)

        val volunteer = Volunteer(role = PSE2)


        // When
        val missionsFiltered = filter.filter(missions, volunteer, "")

        // Then

        assertThat(missionsFiltered).containsExactly(mission1, mission2)
    }

    @Test
    fun `filter with PSE2 volunteer without interest should not return CH and CI missions`() {
        // Given
        val mission1 = aMissionsWithMissingRolesFor(listOf(PSE1, PSE2))
        val mission2 = aMissionsWithMissingRolesFor(listOf(PSE1))
        val mission3 = aMissionsWithMissingRolesFor(listOf(CH_VPSP))
        val mission4 = aMissionsWithMissingRolesFor(listOf(CI))
        val missions = listOf(mission1, mission2, mission3, mission4)

        val volunteer = Volunteer(role = PSE2)


        // When
        val missionsFiltered = filter.filter(missions, volunteer, "")

        // Then

        assertThat(missionsFiltered).containsExactly(mission1, mission2)
    }

    @Test
    fun `filter with PSE1 volunteer without interest should not return PSE2, CH and CI missions`() {
        // Given
        val mission1 = aMissionsWithMissingRolesFor(listOf(PSE1, PSE2))
        val mission2 = aMissionsWithMissingRolesFor(listOf(PSE2))
        val mission3 = aMissionsWithMissingRolesFor(listOf(CH_VPSP))
        val mission4 = aMissionsWithMissingRolesFor(listOf(CI))
        val missions = listOf(mission1, mission2, mission3, mission4)

        val volunteer = Volunteer(role = PSE1)


        // When
        val missionsFiltered = filter.filter(missions, volunteer, "")

        // Then

        assertThat(missionsFiltered).containsExactly(mission1)
    }

    @Test
    fun `complete local mission is a match`() {
        // Given
        val mission = aMission()
                .copy(missingRoles = emptyList(), ul = "ul")


        val volunteer = Volunteer(role = PSE1)


        // When
        val missionsFiltered = filter.filter(listOf(mission), volunteer, "ul")

        // Then

        assertThat(missionsFiltered).containsExactly(mission)
    }

}
