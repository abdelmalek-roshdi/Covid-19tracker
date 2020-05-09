package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.WorldWide

interface StatisticsRepository {
   suspend fun getCaseForContinent(Continent: String): List<Case>
   suspend fun getWorlWidw():WorldWide
}