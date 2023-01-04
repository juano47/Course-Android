package com.delaiglesia.unitconverterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delaiglesia.unitconverterapp.data.Conversion
import com.delaiglesia.unitconverterapp.data.ConversionResult
import com.delaiglesia.unitconverterapp.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(private val converterRepository: ConverterRepository): ViewModel() {

    val selectedConversion: MutableState<Conversion?> = mutableStateOf(null)
    val typedValue: MutableState<Double> = mutableStateOf(0.0)
    val isResultVisible: MutableState<Boolean> = mutableStateOf(false)

    fun getConversions() = listOf(
        Conversion(1, "Celsius to Fahrenheit", "Celsius", "Fahrenheit", 1.8),
        Conversion(2, "Fahrenheit to Celsius", "Fahrenheit", "Celsius", 0.5556),
        Conversion(3, "Miles to Kilometers", "Miles", "Km", 1.609),
        Conversion(4, "Kilometers to Miles", "Km", "Miles", 0.6214),
        Conversion(5, "Pounds to Kilograms", "Pounds", "Kg", 0.4536),
        Conversion(6, "Kilograms to Pounds", "Kg", "Pounds", 2.2046)
    )

    val resultsHistoryList = converterRepository.getAllConversionResults()

    fun saveResult(message1: String, message2: String) {
        //dispatchers.io se usa porque es una operacion de base de datos y no queremos que se bloquee el hilo principal
        viewModelScope.launch(Dispatchers.IO) {
            //no importa si el id es 0, porque la base de datos lo va a ignorar (autoincrement)
            converterRepository.insertConversionResult(ConversionResult(0, message1, message2))
        }
    }

    fun removeResult(conversion: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteConversionResult(conversion)
        }
    }

    fun removeAllResults() {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteAllConversionResults()
        }
    }
}