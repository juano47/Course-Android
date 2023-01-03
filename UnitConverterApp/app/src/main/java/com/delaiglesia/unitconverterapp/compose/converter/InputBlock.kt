package com.delaiglesia.unitconverterapp.compose.converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delaiglesia.unitconverterapp.data.Conversion

@Composable
fun InputBlock(
    conversion: Conversion,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (String) -> Unit
) {
    val inputText: MutableState<String> = remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp).fillMaxWidth()
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            TextField(
                value = inputText.value,
                //it es el valor que se pasa a la funcion lambda (en este caso es el valor del input)
                // y se pasa a la funcion calculate que se pasa como parametro a InputBlock en TopScreen.kt (ver arriba)
                // y se ejecuta en la funcion calculate de TopScreen.kt (ver arriba)
                onValueChange = { inputText.value = it },
                modifier = modifier.fillMaxWidth(0.50f),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface.copy(0.3f),
                ),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp)
            )
            Text(
                text = conversion.convertFrom,
                fontSize = 24.sp,
                modifier = modifier
                    .padding(20.dp, 30.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = modifier.height(20.dp))
        OutlinedButton(
            onClick = {
                if (inputText.value.isNotEmpty()) {
                    calculate(inputText.value)
                } else {
                    Toast.makeText(context, "Please enter a value", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier.fillMaxWidth(1F),
        ) {
            Text(
                text = "Convert",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}

//preview
@Preview(showBackground = true)
@Composable
fun InputBlockPreview() {
    val typedValue: MutableState<Double> = remember { mutableStateOf(0.0) }
    InputBlock(
        conversion = Conversion(
            id = 1,
            convertFrom = "Fahrenheit",
            convertTo = "ft",
            description = "Meters to Feet",
            conversionFactor = 3.28084
        ),
        context = LocalContext.current,
        calculate = {typedValue.value = it.toDouble()}
    )
}