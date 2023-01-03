package com.delaiglesia.unitconverterapp.compose.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.delaiglesia.unitconverterapp.data.Conversion

@Composable
fun ConversionMenu(
    modifier: Modifier = Modifier,
    list: List<Conversion>,
    convert: (Conversion) -> Unit
) {
    //usar by es lo mismo que usar displayingText = remember { mutableStateOf("") } y luego
    //displayingText.value = "algo", con by se hace automaticamente y se puede usar directamente
    //displayingText = "algo"

    //remember es un composable que se encarga de recordar el valor de una variable, si se vuelve a
    //llamar a la funcion con el mismo valor, no se vuelve a ejecutar, si se llama con un valor
    //diferente, se vuelve a ejecutar.
    // Evita perder el estado de la variable cuando se recrea la vista!!
    var displayingText by remember { mutableStateOf("Select the conversion type") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

    Column {
        OutlinedTextField(
            value = displayingText,
            onValueChange = { displayingText = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = "Conversion type") },
            trailingIcon = {
                Icon(icon, contentDescription = "icon",
                    modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach() {
                DropdownMenuItem(
                    onClick = {
                        displayingText = it.description
                        expanded = false
                        convert(it)
                    }) {
                    Text(
                        text = it.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}