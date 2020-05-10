package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo

interface CasesRepository {
    suspend fun getCases() :List<Case>
    suspend fun getCaseForCountry(countryName: String): Case?
    fun getCasesOffline(): LiveData<List<Case>>
    suspend fun getCaseForCountryOffline(countryName: String): Case?
    suspend fun insertAll(cases: List<Case>)
    suspend fun insertCase(case: Case)
    suspend fun getSubscribedCases(): List<Case>
    suspend fun update(case: Case)
    suspend fun updateAll(cases: List<Case>)
    fun getSubscribedCountriesForViews(): LiveData<List<Case>>
}