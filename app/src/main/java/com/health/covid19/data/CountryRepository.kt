package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case

interface CountryRepository {


        suspend fun getCaseForCountryoffline(countryName: String): Case
        suspend fun updateCountry(case:Case)

}