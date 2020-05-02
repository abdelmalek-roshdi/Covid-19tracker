package com.health.covid19.dagger.modules

import android.app.Application
import androidx.room.Room
import com.health.covid19.dagger.scopes.ApplicationScope
import com.health.covid19.data.CasesRepository
import com.health.covid19.data.CasesRepositoryImpl
import com.health.covid19.data.CountryRepository
import com.health.covid19.data.CountryRepositoryImp
import com.health.covid19.net.CaseApi
import com.health.covid19.room.CaseDao
import com.health.covid19.room.LocalDB
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



}