package com.health.covid19.viewmodels

import android.util.Log
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



    fun getCaseForCountry(countryName :String): LiveData<Case> {
        val case : LiveData<Case> = liveData(Dispatchers.IO) {

            countryRepository.getCaseForCountryoffline(countryName)?.let { emit(it) }


        }
        Log.i("TAG","***************************${case.value?.continent}")
        print("***************************${case.value?.continent}")
        return  case
    }

//    fun subscribe(case:Case){
//        countryRepository.updateCountry(case = case)
//    }


}
