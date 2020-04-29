package com.health.covid19.dagger.componenets

import com.health.covid19.MainActivity
import com.health.covid19.dagger.modules.AppModule
import com.health.covid19.dagger.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent{
    fun inject(mainActivity: MainActivity)
}