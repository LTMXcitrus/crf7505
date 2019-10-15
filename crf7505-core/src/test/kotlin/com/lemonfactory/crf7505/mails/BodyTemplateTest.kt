package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import com.lemonfactory.crf7505.utils.Missions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter

private const val DATE_FORMAT = "EEEE dd MMMM"

class BodyTemplateTest {

    private val bodyTemplate = BodyTemplate()

    @Test
    fun bodyTemplateTest() {
        // Given
        val now = LocalDate.of(2019, 10, 15)
        val tomorrow = now.plusDays(1)
        val day1Mission1 = Missions.aMissionWithMissingRoles()
        val day1Mission2 = Missions.aMissionWithMissingRoles()
        val day2Mission1 = Missions.aMissionWithMissingRoles()
        val missionsDay1 = MissionsDay(now, listOf(day1Mission1, day1Mission2))
        val missionsDay2 = MissionsDay(tomorrow, listOf(day2Mission1))

        val missionsDays = listOf(missionsDay1, missionsDay2)


        // When
        val body = bodyTemplate.generateBody(missionsDays)


        // Then
        assertThat(body).isEqualToIgnoringWhitespace("""
            <div><b>Mardi 15 octobre</b>
  <ul>
    <li>name <span style="background-color: #EEEEEE; padding: 5px">Il manque: 1 PSE1</span></li>
    <li>name <span style="background-color: #EEEEEE; padding: 5px">Il manque: 1 PSE1</span></li>
  </ul>
<b>Mercredi 16 octobre</b>
  <ul>
    <li>name <span style="background-color: #EEEEEE; padding: 5px">Il manque: 1 PSE1</span></li>
  </ul>
</div>
        """.trimIndent())

    }

}
