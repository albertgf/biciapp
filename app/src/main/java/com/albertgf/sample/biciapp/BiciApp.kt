package com.albertgf.sample.biciapp

import android.app.Application
import com.albertgf.sample.biciapp.di.component.AppComponent
import com.albertgf.sample.biciapp.di.component.DaggerAppComponent
import com.albertgf.sample.biciapp.di.module.AppModule

class BiciApp : Application() {
    companion object {
        lateinit var  component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)
    }
}