package com.health.covid19.data

import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo
import com.health.covid19.net.CaseApi


class CasesRepositoryImpl(private val caseApi: CaseApi) : CasesRepository {

    override suspend fun getCases(): List<Case>? {
        return  caseApi.getCases()
    }

    override suspend fun getCaseForCountry(countryName: String): Case? {
       return caseApi.getCasesForCountryName(countryName)
    }

    override suspend fun getCasesOffline(): List<Case>? {
        return null
    }

    override suspend fun getCaseForCountryOffline(countryName: String): Case? {
        return null
    }

}