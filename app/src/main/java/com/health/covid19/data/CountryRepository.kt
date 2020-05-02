package com.health.covid19.data

import androidx.lifecycle.LiveData
import com.health.covid19.enitites.Case

interface CountryRepository {

        suspend fun getCase(countryName:String) : Case?
        fun getCaseForCountryoffline(countryName: String): LiveData<Case>

}