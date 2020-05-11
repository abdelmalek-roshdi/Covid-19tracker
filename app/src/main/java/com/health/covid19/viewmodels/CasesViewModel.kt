package com.health.covid19.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.health.covid19.data.CasesRepository
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo
import com.health.covid19.util.Connectivity
import kotlinx.coroutines.*
import javax.inject.Inject

class CasesViewModel @Inject constructor(
    private val casesRepository: CasesRepository, private val connectivity: Connectivity
) : ViewModel() {

    var cases: LiveData<List<Case>> = liveData {
        emit(casesRepository.getCases())
    }

    var offlineCases = casesRepository.getCasesOffline()

    fun searchCountry(prefix: String): List<Case>? {
        return offlineCases.value?.filter { case -> case.country.startsWith(prefix, true) }
    }

    var subscribedCases = liveData<List<Case>> {
        emit(casesRepository.getSubscribedCases())
    }

    var subscribedCountries = casesRepository.getSubscribedCountriesForViews()


    fun unsbcribeAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val cases: List<Case> = casesRepository.getSubscribedCases()
            if (cases.isNotEmpty()) {
                cases.forEach { it.isSubscribed = false }
                casesRepository.updateAll(cases)
            }
        }
    }

    fun refreshDataAsync(): Deferred<Unit> {
       return viewModelScope.async {
            if (connectivity.checkForConnectivity()){
                val cases: List<Case> = casesRepository.getCases()
                val subscribedCases = casesRepository.getSubscribedCases()
                var i = 0
                if (subscribedCases.isNotEmpty()) {
                    cases.forEach {
                        if (i < subscribedCases.size){
                            if (it.country.equals(subscribedCases[i].country)) {
                                it.isSubscribed = true
                                i++
                            }
                        }
                    }
                }
                offlineCases.value?.let {
                    casesRepository.updateAll(it)
                } ?: run {
                    casesRepository.insertAll(cases)
                }
            }
        }
    }

    fun updateCase(case: Case) {
        viewModelScope.launch(Dispatchers.IO) {
            casesRepository.update(case)
        }
    }


    fun getCaseForCountryName(countryName: String): LiveData<Case> {
        val caseForCountry: LiveData<Case> = liveData(Dispatchers.IO) {

            casesRepository.getCaseForCountry(countryName)?.let { emit(it) }
        }
        return caseForCountry
    }

}