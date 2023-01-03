package com.delaiglesia.unitconverterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delaiglesia.unitconverterapp.data.ConverterRepository

class ConverterViewModelFactory(
    private val converterRepository: ConverterRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        ConverterViewModel(converterRepository) as T
}