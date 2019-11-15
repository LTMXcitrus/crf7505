package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.utils.Missions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDate

class BodyTemplateTest {

    private val bodyTemplate = BodyTemplate()

    @Test
    fun bodyTemplateTest() {
        // Given
        val now = LocalDate.of(2019, 10, 15)
        val tomorrow = now.plusDays(1)
        val mission1 = Missions.aMissionWithMissingRoles(now)
        val mission4 = Missions.aMissionWithPartialMissingRoles(now)
        val mission3 = Missions.aMissionWithMissingRoles(tomorrow)

        val localMission = Missions.aLocalReunion(now)

        val activities = Activities(
                listOf(mission1, localMission, mission3),
                listOf(mission1, mission1, mission4, mission3),
                "ul"
        )

        // When
        val body = bodyTemplate.generateBody(activities)

        // Then
        assertThat(body).isEqualToIgnoringWhitespace("""<div>
  <h3>Les missions locales</h3>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
  </ul>
<span style="color: grey;">Mercredi 16 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
  </ul>
<br><span style="color: grey;">Les autres missions</span>
<br>
<br>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>Réunion de l'US (ul)</b></a><span> - Il manque: 15 Participant</span></li>
  </ul>
  <h3>Les missions extérieures</h3>
  <p style="color: red;text-decoration: underline">Comme d'habitude, merci de prévenir le responsable missions avant toute inscription sur Pegass.</p>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1 (09h00-13h00), 1 PSE1</span></li>
  </ul>
<span style="color: grey;">Mercredi 16 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
  </ul>
</div>
        """.trimIndent())

    }

    @Test
    fun bodyTemplateTest_withCompleteLocalMission() {
        // Given
        val now = LocalDate.of(2019, 10, 15)
        val tomorrow = now.plusDays(1)
        val mission1 = Missions.aMissionWithMissingRoles(now)
        val mission4 = Missions.aMissionWithPartialMissingRoles(now)
        val mission3 = Missions.aMissionWithMissingRoles(tomorrow)

        val localMission = Missions.aLocalReunion(now).copy(missingRoles = emptyList())

        val activities = Activities(
                listOf(mission1, localMission, mission3),
                listOf(mission1, mission1, mission4, mission3),
                "ul"
        )

        // When
        val body = bodyTemplate.generateBody(activities)

        // Then
        assertThat(body).isEqualToIgnoringWhitespace("""<div>
  <h3>Les missions locales</h3>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
  </ul>
<span style="color: grey;">Mercredi 16 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
  </ul>
<br><span style="color: grey;">Les autres missions</span>
<br>
<br>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>Réunion de l'US (ul)</b></a><span> </span></li>
  </ul>
  <h3>Les missions extérieures</h3>
  <p style="color: red;text-decoration: underline">Comme d'habitude, merci de prévenir le responsable missions avant toute inscription sur Pegass.</p>
<span style="color: grey;">Mardi 15 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1 (09h00-13h00), 1 PSE1</span></li>
  </ul>
<span style="color: grey;">Mercredi 16 octobre</span>
  <ul>
    <li>00h00-02h00: <a href="https://pegass.croix-rouge.fr/planning-des-activites/activite/id/"style="text-decoration: underline;font-weight: bold;"><b>name (ul)</b></a><span>  - Il manque: 1 PSE1</span></li>
  </ul>
</div>
        """.trimIndent())

    }

    @Test
    fun bodyTemplateTest_with_emptyMissions() {
        // Given
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
