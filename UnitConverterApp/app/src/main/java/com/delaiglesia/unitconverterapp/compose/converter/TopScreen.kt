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

    ConversionMenu(list = list) {
        selectedConversion.value = it
        typedValue.value = 0.0 //reset the value to avoid double entries in the database when
        //re composition happens.
    }
    //if selectedConversion is not null, then show the input block
    //.let es igual a if != null en kotlin y .also es igual a if == null
    selectedConversion.value?.let { selectedConversionFromConversionMenu ->
        InputBlock(
            conversion = selectedConversionFromConversionMenu) { input ->
            typedValue.value = input.toDouble()
        }
    }

    if (typedValue.value != 0.0) {
        //round to 4 decimal places
        val df = DecimalFormat("#.####")
        df.roundingMode = java.math.RoundingMode.DOWN
        val result = typedValue.value.times(selectedConversion.value!!.conversionFactor)
        df.format(result)

        val message1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
        val message2 = "$result ${selectedConversion.value!!.convertTo}"

        saveResult(message1, message2)
        //show result
        ResultBlock(message1, message2)
    }
}


