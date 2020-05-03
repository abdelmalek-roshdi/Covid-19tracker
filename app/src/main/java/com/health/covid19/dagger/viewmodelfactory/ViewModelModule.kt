package com.health.covid19.dagger.viewmodelfactory

import dagger.Module

import dagger.multibindings.IntoMap
import dagger.Binds
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.health.covid19.viewmodels.CasesViewModel
import com.health.covid19.viewmodels.CountryInfoPageViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CasesViewModel::class)
    internal abstract fun bindCasesViewModel(casesViewModel: CasesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryInfoPageViewModel::class)
    internal abstract fun bindCountryInfoPageViewModel(countryInfoPageViewModel: CountryInfoPageViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}