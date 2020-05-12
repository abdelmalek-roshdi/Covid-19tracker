package com.health.covid19.dagger.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.work.WorkerFactory
import com.health.covid19.dagger.scopes.ApplicationScope
import com.health.covid19.dagger.workmanagerfactory.DaggerWorkerFactory
import com.health.covid19.data.*
import com.health.covid19.net.CaseApi
import com.health.covid19.room.CaseDao
import com.health.covid19.room.LocalDB
import com.health.covid19.util.Connectivity
import com.health.covid19.util.sharedPreferencesName
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, AppModule::class])
class DataModule {

    @Provides
    @ApplicationScope
    fun provideCasesRepository(caseApi: CaseApi, caseDao: CaseDao): CasesRepository = CasesRepositoryImpl(caseApi, caseDao)

    @Provides
    @ApplicationScope
    fun provideRoomDB(application: Application) = Room.databaseBuilder(application, LocalDB::class.java,"local_database").fallbackToDestructiveMigration().build()

    @Provides
    @ApplicationScope
    fun provideLocalDBDao(localDB: LocalDB) = localDB.caseDao()

    @Provides
    @ApplicationScope
    fun provideCountryRepository(caseApi: CaseApi, caseDao: CaseDao): CountryRepository = CountryRepositoryImp(caseApi, caseDao)

    @Provides
    @ApplicationScope
    fun provideStatisticsRepository(caseApi: CaseApi, caseDao: CaseDao): StatisticsRepository = StatististicsRepositoryImp(caseApi, caseDao)

    @Provides
    @ApplicationScope
    fun workerFactory(casesRepository: CasesRepository): WorkerFactory {
        return DaggerWorkerFactory(casesRepository)
    }

    @Provides
    @ApplicationScope
    fun provideSharedPrefrences(application: Application): SharedPreferences = application.applicationContext.getSharedPreferences(sharedPreferencesName,
        Context.MODE_PRIVATE)


    @Provides
    @ApplicationScope
    fun providesConnectivity(app:Application) = Connectivity(app)

}