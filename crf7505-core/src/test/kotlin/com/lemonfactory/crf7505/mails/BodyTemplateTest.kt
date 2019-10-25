package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.utils.Missions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDate

private const val DATE_FORMAT = "EEEE dd MMMM"

class BodyTemplateTest {

    private val bodyTemplate = BodyTemplate()

    @Test
    fun bodyTemplateTest() {
        // Given
        val now = LocalDate.of(2019, 10, 15)
        val tomorrow = now.plusDays(1)
        val mission1 = Missions.aMissionWithMissingRoles(now)
        val mission2 = Missions.aMissionWithMissingRoles(now)
        val mission3 = Missions.aMissionWithMissingRoles(tomorrow)

        val localMission = Missions.aLocalReunion()

        val activities = Activities(
                listOf(mission1, localMission, mission3),
                listOf(mission1, mission2, mission3),
                "ul"
        )



        // When
        val body = bodyTemplate.generateBody(activities)


        // Then
        assertThat(body).isEqualToIgnoringWhitespace("""<div>
  <h3>Les missions locales</h3>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li><b>name</b><span> - Il manque: 1 PSE1</span></li>
  </ul>
<span style="color: grey;">Mercredi 16 octobre</span>
  <ul>
    <li><b>name</b><span> - Il manque: 1 PSE1</span></li>
  </ul>
<br><span>Les autres missions</span>
<br>
<span style="color: grey;">Vendredi 25 octobre</span>
  <ul>
    <li><b>Réunion de l'US</b><span> - Il manque: 15 Participant</span></li>
  </ul>
  <h3>Les missions extérieures</h3>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li><b>name</b><span> - Il manque: 1 PSE1</span></li>
    <li><b>name</b><span> - Il manque: 1 PSE1</span></li>
  </ul>
<span style="color: grey;">Mercredi 16 octobre</span>
  <ul>
    <li><b>name</b><span> - Il manque: 1 PSE1</span></li>
  </ul>
</div>
        """.trimIndent())

    }

    @Test
    fun bodyTemplateTest_with_emptyMissions() {
        // Given
        val now = LocalDate.of(2019, 10, 15)
        val tomorrow = now.plusDays(1)
        val mission1 = Missions.aMissionWithMissingRoles(now)
        val mission2 = Missions.aMissionWithMissingRoles(now)
        val mission3 = Missions.aMissionWithMissingRoles(tomorrow)

        val localMission = Missions.aLocalReunion()

        val activities = Activities(
                emptyList(),
                emptyList(),
                "ul"
        )



        // When
        val body = bodyTemplate.generateBody(activities)


        // Then
        assertThat(body).isEqualToIgnoringWhitespace("""
            <div>
  <h3>Les missions locales</h3>
<span>Pas de missions locales</span>
  <h3>Les missions extérieures</h3>
<span>Pas de missions extérieures</span></div>
        """.trimIndent())

    }

}
