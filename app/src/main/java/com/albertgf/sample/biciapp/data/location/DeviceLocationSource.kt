package com.albertgf.sample.biciapp.data.location

import com.albertgf.sample.biciapp.domain.location.Location

interface DeviceLocationSource {
    suspend fun getDeviceLocation(): Location
}