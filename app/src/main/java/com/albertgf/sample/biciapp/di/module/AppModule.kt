package com.albertgf.sample.biciapp.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.albertgf.sample.biciapp.BiciApp
import com.albertgf.sample.biciapp.data.BiciDatabase
import com.albertgf.sample.biciapp.data.BiciDatabase.Companion.DB_NAME
import com.albertgf.sample.biciapp.data.stations.DiskStationDataSource
import com.albertgf.sample.biciapp.data.stations.StationsDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: BiciApp) {
    @Provides
    @Singleton fun provideApp() = app

    @Provides
    fun provideContext(): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideBiciDataBase(): BiciDatabase {
        return Room.databaseBuilder(app.applicationContext, BiciDatabase::class.java, DB_NAME).build()
    }

    @Provides @Singleton
    fun diskStationDataSource(dataSource: DiskStationDataSource): StationsDataSource = dataSource

}