package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.model.Volunteer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HeaderTemplateTest {

    val headerTemplate = HeaderTemplate()

    @Test
    fun headerTemplateTest() {
        // Given

        // When
        val header = headerTemplate.generateHeader(Volunteer(firstname = "toi"), "header", "Emmanuel")

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
