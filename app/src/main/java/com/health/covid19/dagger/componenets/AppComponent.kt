package com.health.covid19.dagger.componenets

import com.health.covid19.MainActivity
import com.health.covid19.dagger.modules.AppModule
import com.health.covid19.dagger.modules.DataModule
import com.health.covid19.dagger.modules.ServiceModule
import com.health.covid19.dagger.scopes.ApplicationScope
import com.health.covid19.dagger.viewmodelfactory.ViewModelModule
import com.health.covid19.ui.main.RatesFragment
import dagger.Component


@ApplicationScope
@Component(modules = [AppModule::class, ServiceModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(ratesFragment: RatesFragment)
}