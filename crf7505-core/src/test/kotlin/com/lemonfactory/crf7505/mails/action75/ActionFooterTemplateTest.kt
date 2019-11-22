package com.lemonfactory.crf7505.mails.action75

import org.assertj.core.api.Assertions
import org.junit.Test

class ActionFooterTemplateTest {

    private val footerTemplate = ActionFooterTemplate()

    @Test
    fun generateFooterTest() {
        // Given

        // When
        val footer = footerTemplate.generateFooter()

        // Then
        Assertions.assertThat(footer).isEqualToIgnoringWhitespace("<p>Cordialement, l'équipe du V</p>")
    }


}
