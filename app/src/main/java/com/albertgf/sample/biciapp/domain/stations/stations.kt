package com.albertgf.sample.biciapp.domain.stations

data class StationDomain (
        val id:String,
        val emptySlots: Int,
        val freeBikes: Int,
        val longitude: Double,
        val latitude: Double,
        val name: String,
        val timestamp: String,
        val address: String,
        val districtCode: String,
        val status: String,
        val zip: String,
        val ebikes: Boolean
)

data class StationMinimalDomain (
        val id: String,
        val name: String,
        val emptySlots: Int,
        val freeBikes: Int,
        val longitude: Double,
        val latitude: Double,
        val status: String ,
        val ebikes: Boolean
)