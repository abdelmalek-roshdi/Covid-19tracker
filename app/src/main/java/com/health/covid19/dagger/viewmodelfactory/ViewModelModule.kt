package com.health.covid19.dagger.viewmodelfactory

import dagger.Module

import dagger.multibindings.IntoMap
import dagger.Binds
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.health.covid19.viewmodels.CasesViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CasesViewModel::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindEditPlaceViewModel(editPlaceViewModel: CasesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}