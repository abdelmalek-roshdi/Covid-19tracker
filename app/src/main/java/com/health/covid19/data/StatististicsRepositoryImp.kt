package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case
import com.health.covid19.net.CaseApi
import com.health.covid19.room.CaseDao

class StatististicsRepositoryImp(private val caseApi: CaseApi, private val caseDao: CaseDao): StatisticsRepository {

   override suspend fun getCaseForContinent(countryName: String): List<Case>{
        return caseDao.getCaseBycontinent(countryName)
    }

}