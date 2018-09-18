package com.albertgf.sample.apiclient

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BiciApiTest {
    private lateinit var apiClient: BiciApiClient

    @Before
    fun setUp() {
        apiClient = BiciApiClient();
    }

    @Test
    fun getBicingNetwork() {
        val values = apiClient.getStations().right().get()

        Assert.assertTrue(values?.network?.id.equals("bicing"))
    }

    @Test
    fun getStationsNetwork() {
        val values = apiClient.getStations().right().get()

        Assert.assertTrue(values?.network?.stations?.isNotEmpty() ?: false)
    }
}