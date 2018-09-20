package com.albertgf.sample.biciapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.albertgf.sample.biciapp.data.BiciDatabase.Companion.VERSION
import com.albertgf.sample.biciapp.data.stations.StationsDao


@Database(entities = [StationDisk::class], version = VERSION, exportSchema = false)
abstract class BiciDatabase : RoomDatabase() {

    abstract fun stationsDataDao(): StationsDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "bicis.db"

        @Volatile private var instance: BiciDatabase? = null

        fun getInstance(context: Context): BiciDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): BiciDatabase {
            return Room.databaseBuilder(context, BiciDatabase::class.java, DB_NAME).build()
        }
    }
}