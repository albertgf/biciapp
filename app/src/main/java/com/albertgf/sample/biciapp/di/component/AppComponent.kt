package com.albertgf.sample.biciapp.di.component

import android.content.Context
import com.albertgf.sample.biciapp.BiciApp
import com.albertgf.sample.biciapp.data.BiciDatabase
import com.albertgf.sample.biciapp.data.stations.DiskStationDataSource
import com.albertgf.sample.biciapp.data.stations.StationsDataSource
import com.albertgf.sample.biciapp.di.module.AppModule
import com.albertgf.sample.biciapp.ui.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class)) interface AppComponent {
    fun inject(app: BiciApp)

    fun inject(activity: MainActivity)

    fun context(): Context
    fun biciDataBase(): BiciDatabase
    fun diskStationDataSource() : StationsDataSource
    fun apiStationDataSource() : StationsDataSource
}