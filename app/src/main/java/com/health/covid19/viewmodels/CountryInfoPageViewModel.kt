package com.health.covid19.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.health.covid19.dagger.modules.DataModule
import com.health.covid19.data.CasesRepository
import com.health.covid19.data.CountryRepository
import com.health.covid19.data.CountryRepositoryImp
import com.health.covid19.enitites.Case
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryInfoPageViewModel  @Inject constructor(
    private val countryRepository: CountryRepository

): ViewModel() {

    var isOnline = true

    fun getCaseForCountry(countryName :String): LiveData<Case> {
        val case : LiveData<Case> = liveData(Dispatchers.IO) {
            if(isOnline) {
                countryRepository.getCase(countryName)?.let { emit(it) }
            }
            else{getCaseForCountry(countryName)}
        }
        return  case
    }



}
