package com.health.covid19.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.health.covid19.data.CasesRepository
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CasesViewModel @Inject constructor(
    private val casesRepository: CasesRepository
): ViewModel() {

      var cases:LiveData<List<Case>> = liveData {
          emit(casesRepository.getCases())
      }

    var offlineCases = casesRepository.getCasesOffline()

    var subscribedCases = liveData<List<Case>> {
        emit(casesRepository.getSubscribedCases())
    }

    var  subscribedCountries = casesRepository.getSubscribedCountriesForViews()


    fun unsbcribeAllCountries(){
       viewModelScope.launch(Dispatchers.IO) {
           val cases :List<Case> = casesRepository.getSubscribedCases()
           if (cases.isNotEmpty()) {
               cases.forEach { it.isSubscribed = false }
               casesRepository.updateAll(cases)
           }
       }
    }

    fun updateCase(case: Case){
        viewModelScope.launch(Dispatchers.IO) {
            casesRepository.update(case)
        }
    }


    fun getCaseForCountryName(countryName :String): LiveData<Case>{
        val caseForCountry : LiveData<Case> = liveData(Dispatchers.IO) {

            casesRepository.getCaseForCountry(countryName)?.let { emit(it) }
        }
        return  caseForCountry
    }

}