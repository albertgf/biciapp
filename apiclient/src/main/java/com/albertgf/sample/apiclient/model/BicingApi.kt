package com.albertgf.sample.apiclient.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose



data class StationsApi (

    @SerializedName("network") val network: NetworkApiClient
)

data class NetworkApiClient (
    @SerializedName("company") val company: List<String>? = null,
    @SerializedName("href") val href: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("location") val location: LocationApiCLient? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("stations") val stations: List<StationApiCLient>? = null
)

data class LocationApiCLient (
        @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("latitude")val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

data class StationApiCLient (
    @SerializedName("empty_slots") val emptySlots : Int,
    @SerializedName("extra")val extra: ExtraApiClient,
    @SerializedName("free_bikes")val freeBikes: Int,
    @SerializedName("id") val id:String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude")val longitude: Double,
    @SerializedName("name") val name: String,
    @SerializedName("timestamp") val timestamp: String
)

data class ExtraApiClient (
    @SerializedName("NearbyStationList")val nearbyStations: List<Int>,
    @SerializedName("address") val address: String,
    @SerializedName("districtCode")val districtCode: String,
    @SerializedName("status") val status: String ,
    @SerializedName("uid") val uid: Int,
    @SerializedName("zip") val zip: String,
    @SerializedName("ebikes") val ebikes: Boolean
)