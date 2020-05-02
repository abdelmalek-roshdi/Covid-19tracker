package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case
import com.health.covid19.net.CaseApi
import com.health.covid19.room.CaseDao

class CountryRepositoryImp (private val caseApi: CaseApi, private val caseDao: CaseDao):CountryRepository{
    override suspend fun getCase(countryName:String): Case? {
        return caseApi.getCasesForCountryName(countryName = countryName)
    }

    override fun getCaseForCountryoffline(countryName: String): LiveData<Case> {
        return caseDao.getCountryCaseByname(countryName)
    }

}