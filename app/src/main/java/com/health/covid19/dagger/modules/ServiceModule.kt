package com.health.covid19.dagger.modules

import com.health.covid19.dagger.scopes.ApplicationScope
import com.health.covid19.data.CasesRepository
import com.health.covid19.data.CasesRepositoryImpl
import com.health.covid19.net.CaseApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton
@Module(includes = [NetworkModule::class])
class ServiceModule {
    @Provides
    @ApplicationScope
    fun provideCaseApi(@Named(NetworkModule.BASE_URL) baseURL: String, client: OkHttpClient, gsonConverterFactory: GsonConverterFactory, moshiConverterFactory: MoshiConverterFactory) =
        Retrofit
        .Builder()
        .baseUrl(baseURL)
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .addConverterFactory(moshiConverterFactory)
        .build()
        .create(CaseApi::class.java)


}