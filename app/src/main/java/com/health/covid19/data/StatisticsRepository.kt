package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case

interface StatisticsRepository {
   suspend fun getCaseForContinent(countryName: String): List<Case>
}