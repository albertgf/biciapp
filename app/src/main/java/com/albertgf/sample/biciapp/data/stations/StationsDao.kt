package com.albertgf.sample.biciapp.data.stations

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.albertgf.sample.biciapp.data.BaseDao
import com.albertgf.sample.biciapp.data.StationDisk
import com.albertgf.sample.biciapp.data.StationMinimal

@Dao
abstract class StationsDao : BaseDao<StationDisk> {

    @Query ("SELECT * FROM stations WHERE id = :stationId LIMIT 1")
    abstract fun searchById(stationId: Int) : StationDisk

    @Query("SELECT COUNT(*) FROM stations")
    abstract fun count() : Int

    @Insert
    abstract fun insertAll(stations: List<StationDisk>)

    @Query ("DELETE FROM stations")
    abstract fun deleteAll()

    @Transaction
    open fun updateData(stations: List<StationDisk>) {
        deleteAll()
        insertAll(stations)
    }

    @Query("SELECT * FROM stations WHERE id = :stationId")
    abstract fun getStation(stationId: String) : StationDisk

    @Query ("SELECT * FROM stations")
    abstract fun getStations(): List<StationDisk>

    @Query ("SELECT id, name, empty_slots, free_bikes, longitude, latitude, status, ebikes FROM stations")
    abstract fun getStationsMinimal(): List<StationMinimal>
}