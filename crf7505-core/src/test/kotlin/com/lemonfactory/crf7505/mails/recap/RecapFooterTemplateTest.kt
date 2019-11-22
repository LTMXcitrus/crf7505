package com.lemonfactory.crf7505.mails.recap

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RecapFooterTemplateTest {

    val footerTemplate = RecapFooterTemplate()

    @Test
    fun generateFooterTest() {
        // Given

        // When
        val footer = footerTemplate.generateFooter("footer")

        // Then
        assertThat(footer).isEqualToIgnoringWhitespace("<p>footer</p>")
    }

}
