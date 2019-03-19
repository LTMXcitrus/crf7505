package com.lemonfactory.pegass.client

import com.lemonfactory.pegass.client.api.activity.PegassActivity
import com.nhaarman.mockito_kotlin.any
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class PegassClientTest {


    val pegassConnectorMock = Mockito.mock(PegassConnector::class.java)

    val pegassClient: PegassClient = PegassClient(pegassConnectorMock)

    @Before
    fun setUp() {
        `when`(pegassConnectorMock.getPage(any())).thenReturn(pegassResponse)
    }

    @Test
    fun getActivities_test() {
        // Given

        // When
        val response: List<PegassActivity> = pegassClient.getActivities()

        // Then
        assert(response.isNotEmpty())
    }


}