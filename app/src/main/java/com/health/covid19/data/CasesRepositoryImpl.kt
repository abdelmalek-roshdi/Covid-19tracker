package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case
import com.health.covid19.net.CaseApi
import com.health.covid19.room.CaseDao


class CasesRepositoryImpl constructor(private val caseApi: CaseApi, private val caseDao: CaseDao) : CasesRepository {

    override suspend fun getCases(): List<Case> {
        return  caseApi.getCases()
    }

    override suspend fun getCaseForCountry(countryName: String): Case? {
       return caseApi.getCasesForCountryName(countryName)
    }

    override fun getCasesOffline(): LiveData<List<Case>> {
       return caseDao.getAllCases()
    }

    override suspend fun getCaseForCountryOffline(countryName: String): Case? {
        return null
    }

    override suspend fun insertAll(cases: List<Case>) {
        caseDao.insertAll(cases)
    }

    override suspend fun getSubscribedCases(): List<Case> {
        return  caseDao.getSubscribedCases()
    }

    override suspend fun update(case: Case) {
        caseDao.update(case)
    }

    override suspend fun updateAll(cases: List<Case>) {
        caseDao.updateAll(cases)
    }

    override suspend fun insertCase(case: Case) {
        caseDao.insert(case)
    }

}