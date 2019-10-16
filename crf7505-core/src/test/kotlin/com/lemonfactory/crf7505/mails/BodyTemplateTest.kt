package com.lemonfactory.crf7505.mails

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



        // When
        val body = bodyTemplate.generateBody(listOf(mission1, mission2, mission3))


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
