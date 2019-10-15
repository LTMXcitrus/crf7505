package com.lemonfactory.crf7505.mails

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FooterTemplateTest {

    val footerTemplate = FooterTemplate()

    @Test
    fun generateFooterTest() {
        // Given

        // When
        val footer = footerTemplate.generateFooter("footer")

        // Then
        assertThat(footer).isEqualToIgnoringWhitespace("<p>footer</p>")
    }

}
