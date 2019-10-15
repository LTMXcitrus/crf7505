package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Volunteer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HeaderTemplateTest {

    val headerTemplate = HeaderTemplate()

    @Test
    fun headerTemplateTest() {
        // Given

        // When
        val header = headerTemplate.generateHeader(Volunteer(firstname = "toi"), "header")

        // Then
        assertThat(header).isEqualToIgnoringWhitespace("""
                    <div>
                        <p>Bonjour toi,</p>
                        <p>header</p>
                        <p>Tu trouveras ci-après le récapitulatif de la semaine.</p>
                        <p>Le responsable missions de la semaine est <b>Emmanuel</b></p>
                    </div>
                    """)
    }

}
