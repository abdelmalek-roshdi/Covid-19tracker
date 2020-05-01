package com.health.covid19.dagger.modules

import com.health.covid19.dagger.scopes.ApplicationScope
import com.health.covid19.data.CasesRepository
import com.health.covid19.data.CasesRepositoryImpl
import com.health.covid19.net.CaseApi
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    @ApplicationScope
    fun provideCasesRepository(caseApi: CaseApi): CasesRepository = CasesRepositoryImpl(caseApi)
}