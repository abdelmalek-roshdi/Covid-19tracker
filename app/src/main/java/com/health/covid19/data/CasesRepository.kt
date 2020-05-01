package com.health.covid19.data

import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo

interface CasesRepository {
    suspend fun getCases() :List<Case>?
    suspend fun getCaseForCountry(countryName: String): Case?
    suspend fun getCasesOffline(): List<Case>?
    suspend fun getCaseForCountryOffline(countryName: String): Case?
}