package com.delaiglesia.unitconverterapp

import androidx.lifecycle.ViewModel
import com.delaiglesia.unitconverterapp.data.Conversion

class ConverterViewModel: ViewModel() {

    fun getConversions() = listOf(
        Conversion(1, "Celsius to Fahrenheit", "Celsius", "Fahrenheit", 1.8),
        Conversion(2, "Fahrenheit to Celsius", "Fahrenheit", "Celsius", 0.5556),
        Conversion(3, "Miles to Kilometers", "Miles", "Km", 1.609),
        Conversion(4, "Kilometers to Miles", "Km", "Miles", 0.6214),
        Conversion(5, "Pounds to Kilograms", "Pounds", "Kg", 0.4536),
        Conversion(6, "Kilograms to Pounds", "Kg", "Pounds", 2.2046)
    )
}