package com.albertgf.sample.biciapp.data

import android.arch.persistence.room.*

@Entity(tableName = "stations",
        indices = [Index("id")])
data class StationDisk(
        @PrimaryKey var id:String,
        @ColumnInfo(name = "empty_slots") val emptySlots: Int,
        @ColumnInfo(name = "free_bikes") val freeBikes: Int,
        @ColumnInfo(name = "longitude") val longitude: Double,
        @ColumnInfo(name = "latitude") val latitude: Double,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "timestamp") val timestamp: String,
        @ColumnInfo(name = "address") val address: String,
        @ColumnInfo(name = "districtCode")val districtCode: String,
        @ColumnInfo(name = "status") val status: String ,
        @ColumnInfo(name = "zip") val zip: String,
        @ColumnInfo(name = "ebikes") val ebikes: Boolean
)

data class StationMinimal (
        val id: String,
        @ColumnInfo(name = "empty_slots") val emptySlots: Int,
        @ColumnInfo(name = "free_bikes") val freeBikes: Int,
        val longitude: Double,
        val latitude: Double,
        val status: String ,
        val ebikes: Boolean
)