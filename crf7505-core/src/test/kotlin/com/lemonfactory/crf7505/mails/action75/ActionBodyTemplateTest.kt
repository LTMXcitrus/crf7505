package com.lemonfactory.crf7505.mails.action75

import com.lemonfactory.crf7505.utils.Missions
import org.assertj.core.api.Assertions
import org.junit.Test
import java.time.LocalDate

class ActionBodyTemplateTest {
    private val bodyTemplate = ActionBodyTemplate()

    @Test
    fun bodyTemplateTest() {
        // Given
        val now = LocalDate.of(2019, 10, 15)
        val tomorrow = now.plusDays(1)
        val mission1 = Missions.aMissionWithMissingRoles(now)
        val mission4 = Missions.aMissionWithPartialMissingRoles(now)
        val mission3 = Missions.aMissionWithMissingRoles(tomorrow)


        val activities = listOf(mission1, mission1, mission4, mission3)

        // When
        val body = bodyTemplate.generateBody(activities)

        // Then
        Assertions.assertThat(body).isEqualToIgnoringWhitespace("""<div>
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

}
