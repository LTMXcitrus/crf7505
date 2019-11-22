package com.lemonfactory.crf7505.mails.recap

import com.lemonfactory.crf7505.domain.model.Volunteer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RecapHeaderTemplateTest {

    val headerTemplate = RecapHeaderTemplate()

    @Test
    fun headerTemplateTest() {
        // Given

        // When
        val header = headerTemplate.generateHeader(RecapHeader(Volunteer(firstname = "toi"), "header", "Emmanuel"))

        // Then
        assertThat(header).isEqualToIgnoringWhitespace("""
                    <div>
                        <p>Bonjour toi,</p>
                        <p>Tu trouveras ci-après le récapitulatif de la semaine.<br>Le responsable missions est <b>Emmanuel</b></p>
                        <p>header</p>
                    </div>
                    """)
    }

}
