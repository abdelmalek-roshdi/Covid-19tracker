package com.health.covid19.app

import android.app.Application
import com.health.covid19.dagger.componenets.AppComponent
import com.health.covid19.dagger.componenets.DaggerAppComponent
import com.health.covid19.dagger.modules.AppModule


class Covid19TrackerApp: Application() {
    lateinit var covid19TrackerComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        covid19TrackerComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}