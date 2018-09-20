package com.albertgf.sample.biciapp

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.albertgf.sample.biciapp.data.BiciDatabase
import com.albertgf.sample.biciapp.data.StationDisk
import com.albertgf.sample.biciapp.data.stations.StationsDao
import com.albertgf.sample.biciapp.domain.stations.StationDomain

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BiciDataBaseTest {
    private lateinit var stationDao: StationsDao
    private lateinit var biciDB: BiciDatabase

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getTargetContext()
        biciDB = Room.inMemoryDatabaseBuilder(context, BiciDatabase::class.java).build()
        stationDao = biciDB.stationsDataDao()
    }


    @Test
    fun insertOneStation() {
        val station = StationDisk("1",0,0,0.0,0.0,"","","","","","",false)
        stationDao.insert(station)
        val values = stationDao.getStations()
        assertEquals(station.id, values.get(0).id)
    }
}
