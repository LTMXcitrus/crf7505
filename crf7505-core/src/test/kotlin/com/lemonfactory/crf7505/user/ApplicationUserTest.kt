package com.lemonfactory.crf7505.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ApplicationUserTest {


    @Test
    fun `getId should return username`() {
        // Given
        val applicationUser = ApplicationUser(
                "username",
                "firstname",
                "password",
                "userStructure",
                false

        )

        // When
        val id = applicationUser.getId()

        // Then
        assertThat(id).isEqualTo("username")
    }
}