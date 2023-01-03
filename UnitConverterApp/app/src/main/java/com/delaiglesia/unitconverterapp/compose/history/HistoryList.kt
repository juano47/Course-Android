package com.delaiglesia.unitconverterapp.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.delaiglesia.unitconverterapp.data.ConversionResult

@Composable
fun HistoryList(
    historyResults: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(
            items = historyResults.value,
            key = { item -> item.id }
        ) { item: ConversionResult ->
            HistoryItem(
                messagePart1 = item.messagePart1,
                messagePart2 = item.messagePart2,
                onClose = { onCloseTask(item) },
                modifier = modifier
            )
        }
    }
}