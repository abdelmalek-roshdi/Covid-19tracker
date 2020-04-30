package com.health.covid19.dagger.modules

import com.health.covid19.dagger.scopes.ApplicationScope
import com.health.covid19.net.CaseApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object{
        internal const val BASE_URL = "https://corona.lmao.ninja/v2/"
    }

    @Provides
    @ApplicationScope
    @Named(BASE_URL)
    fun provideBaseUrlString() = BASE_URL

    @Provides
    @ApplicationScope
    fun provideClient() = OkHttpClient()

    @Provides
    @ApplicationScope
    fun provideGsonConverer() = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    fun provideMoshiConverer() = MoshiConverterFactory.create()

}