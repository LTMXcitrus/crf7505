package com.lemonfactory.crf7505.mails.action75

import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.mails.recap.RecapHeader
import org.assertj.core.api.Assertions
import org.junit.Test

class ActionHeaderTemplateTest {

    val headerTemplate = ActionHeaderTemplate()

    @Test
    fun headerTemplateTest() {
        // Given

        // When
        val header = headerTemplate.generateHeader()

        // Then
        Assertions.assertThat(header).isEqualToIgnoringWhitespace("""
                    <div>
  <p>Bonjour,</p>
  <p>Ci-joint un récapitulatif des postes de la semaine.<br></p>
</div>
                    """)
    }


}
