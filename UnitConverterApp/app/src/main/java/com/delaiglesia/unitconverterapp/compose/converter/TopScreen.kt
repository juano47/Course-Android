package com.delaiglesia.unitconverterapp.compose.converter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.delaiglesia.unitconverterapp.data.Conversion
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    saveResult: (String, String) -> Unit
) {
    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val typedValue: MutableState<Double> = remember { mutableStateOf(0.0) }
    val isResultVisible: MutableState<Boolean> = remember { mutableStateOf(false) }

    ConversionMenu(list = list) {
        selectedConversion.value = it
    }

    InputBlock(
        conversion = selectedConversion.value,
        calculate = { input ->
            typedValue.value = input.toDouble()
        },
        emptyInput = {
            isResultVisible.value = false
        }
    )

    val message1 = remember { mutableStateOf("") }
    val message2 = remember { mutableStateOf("") }
    if (typedValue.value != 0.0) {
        //round to 4 decimal places
        val df = DecimalFormat("#.####")
        df.roundingMode = java.math.RoundingMode.DOWN
        val result = typedValue.value.times(selectedConversion.value!!.conversionFactor)
        val resultFormatted = df.format(result)

        message1.value = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
        message2.value = "$resultFormatted ${selectedConversion.value!!.convertTo}"

        saveResult(message1.value, message2.value)
        isResultVisible.value = true

    }
    typedValue.value = 0.0

    ResultBlock(message1.value, message2.value, isResultVisible.value)

}


