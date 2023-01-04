package com.delaiglesia.unitconverterapp.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delaiglesia.unitconverterapp.ConverterViewModel
import com.delaiglesia.unitconverterapp.ConverterViewModelFactory
import com.delaiglesia.unitconverterapp.compose.converter.TopScreen
import com.delaiglesia.unitconverterapp.compose.history.HistoryScreen

@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = factory)
) {
    val list = converterViewModel.getConversions()
    val historyResults = converterViewModel.resultsHistoryList.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.typedValue,
                    converterViewModel.isResultVisible,
                    isLandScape = true,
                ) { message1, message2 ->
                    converterViewModel.saveResult(message1, message2)
                }
                Spacer(modifier = modifier.width(10.dp))
                HistoryScreen(
                    historyResults,
                    removeResult = { result -> converterViewModel.removeResult(result) },
                    removeAllResults = { converterViewModel.removeAllResults() }
                )
            }
        }
        else -> {
            Column(modifier = modifier.padding(30.dp)) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.typedValue,
                    converterViewModel.isResultVisible,
                    isLandScape = false,
                ) { message1, message2 ->
                    converterViewModel.saveResult(message1, message2)
                }
                Spacer(modifier = modifier.height(20.dp))
                HistoryScreen(
                    historyResults,
                    removeResult = { result -> converterViewModel.removeResult(result) },
                    removeAllResults = { converterViewModel.removeAllResults() }
                )
            }
        }
    }
}