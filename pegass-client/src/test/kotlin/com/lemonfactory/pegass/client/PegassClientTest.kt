package com.lemonfactory.pegass.client

import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.nhaarman.mockito_kotlin.any
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.time.LocalDateTime


class PegassClientTest {


    val pegassConnectorMock = Mockito.mock(PegassSession::class.java)

    val pegassClient: PegassClient = PegassClient()

    @Before
    fun setUp() {
        `when`(pegassConnectorMock.getPage(any())).thenReturn(pegassResponse)
    }

    @Test
    fun getActivities_test() {
        // Given

        // When
        val response: List<PegassActivity> = pegassClient.getActivities(pegassConnectorMock, LocalDateTime.now(), LocalDateTime.now())

        // Then
        assert(response.isNotEmpty())
    }


}