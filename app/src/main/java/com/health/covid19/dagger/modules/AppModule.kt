package com.health.covid19.dagger.modules

import android.app.Application
import android.content.Context
import com.health.covid19.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @ApplicationScope
    fun provideContext(): Context = app
}