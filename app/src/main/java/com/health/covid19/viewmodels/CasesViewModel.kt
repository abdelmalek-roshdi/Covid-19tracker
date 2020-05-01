package com.health.covid19.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.health.covid19.data.CasesRepository
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CasesViewModel @Inject constructor(
    private val casesRepository: CasesRepository
): ViewModel() {

      var cases: LiveData<List<Case>> = liveData(Dispatchers.IO){
          casesRepository.getCases()?.let { emit(it) }
     }

    fun getCaseForCountryName(countryName :String): LiveData<Case>{
        val caseForCountry : LiveData<Case> = liveData(Dispatchers.IO) {

            casesRepository.getCaseForCountry(countryName)?.let { emit(it) }
        }
        return  caseForCountry
    }

}