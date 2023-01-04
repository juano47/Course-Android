package com.delaiglesia.unitconverterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delaiglesia.unitconverterapp.data.ConverterRepository
import javax.inject.Inject

//las clases que tienen un constructor se les puede agregar directamente el @Inject
//pero las interfaces o clases abstractas no, por eso se hace con un Module
class ConverterViewModelFactory @Inject constructor(
    private val converterRepository: ConverterRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        ConverterViewModel(converterRepository) as T
}