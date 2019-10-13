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
        val header = headerTemplate.generateHeader(Volunteer(firstname = "toi"))

        // Then
        assertThat(header).isEqualToIgnoringWhitespace(
                """<div><span>Bonjour toi,</span><br><span>Tu trouveras ci-après le récapitulatif de la semaine.</span><br><span>Le responsable missions de la semaine est <b>Emmanuel</b></span><br></div>"""
        )
    }

}