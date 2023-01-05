package com.delaiglesia.unitconverterapp.compose.converter

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.delaiglesia.unitconverterapp.data.Conversion
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    typedValue: MutableState<Double>,
    isResultVisible: MutableState<Boolean>,
    isLandScape: Boolean,
    saveResult: (String, String) -> Unit
) {
    //cada vez que se produce una recomposicion, se va a mostrar el toast, esto se conoce como side effect!
    //Toast.makeText(LocalContext.current, "A test message that should only be displayed once", Toast.LENGTH_SHORT).show()
    //Como evitarlo? Usar side effects API's.
    val context = LocalContext.current
    LaunchedEffect(
        key1 = true, //true es un valor que no cambia, por lo que solo se ejecutara una vez
        block = {
        Toast.makeText(context, "A test message that should only be displayed once", Toast.LENGTH_SHORT).show()
    })

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ConversionMenu(
            list = list,
            isLandScape = isLandScape,
            convert = { selectedConversion.value = it })
        InputBlock(
            conversion = selectedConversion.value,
            isLandScape = isLandScape,
            calculate = { input ->
                typedValue.value = input.toDouble()
            },
            emptyInput = {
                isResultVisible.value = false
            }
        )

        val message1 = rememberSaveable { mutableStateOf("") }
        val message2 = rememberSaveable { mutableStateOf("") }
        if (typedValue.value != 0.0) {
            //round to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = java.math.RoundingMode.DOWN
            val result = typedValue.value.times(selectedConversion.value!!.conversionFactor)
            val resultFormatted = df.format(result)

            message1.value =
                "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            message2.value = "$resultFormatted ${selectedConversion.value!!.convertTo}"

            saveResult(message1.value, message2.value)
            isResultVisible.value = true

        }
        typedValue.value = 0.0

        ResultBlock(message1.value, message2.value, isResultVisible.value)
    }
}


