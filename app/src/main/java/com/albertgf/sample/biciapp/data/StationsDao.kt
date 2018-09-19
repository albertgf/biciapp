package com.albertgf.sample.biciapp.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface StationsDao {

    @Query ("SELECT * FROM stations WHERE id = :stationId LIMIT 1")
    fun searchById(stationId: Int) : StationDisk

    @Query("SELECT COUNT(*) FROM stations")
    fun count() : Int

    @Insert
    fun insertAll(stations: List<StationDisk>)

    @Query ("DELETE FROM stations")
    fun deleteAll()
}