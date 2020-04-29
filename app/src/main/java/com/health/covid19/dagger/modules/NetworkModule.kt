package com.health.covid19.dagger.modules

import com.health.covid19.net.CaseApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object{
        private const val BASE_URL = "https://corona.lmao.ninja/v2/"
    }
    @Provides
    @Singleton
    fun provideCaseApi() =  Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()
        .create(CaseApi::class.java)
}