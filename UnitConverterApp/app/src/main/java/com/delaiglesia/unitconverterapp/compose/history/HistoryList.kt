package com.delaiglesia.unitconverterapp.compose.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.delaiglesia.unitconverterapp.data.ConversionResult

@Composable
fun HistoryList(
    historyResults: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(elevation = 10.dp, color = MaterialTheme.colors.surface,){
        LazyColumn(
            modifier = modifier
                .border(
                    border = BorderStroke(0.5.dp, color = Color.Gray),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            items(
                items = historyResults.value,
                key = { item -> item.id }
            ) { item: ConversionResult ->
                //if is the last item, we don't want to show the divider
                var showDivider = true
                if (item == historyResults.value.last()) {
                    showDivider = false
                }

                HistoryItem(
                    messagePart1 = item.messagePart1,
                    messagePart2 = item.messagePart2,
                    onClose = { onCloseTask(item) },
                    modifier = modifier,
                    showDivider = showDivider
                )
            }
        }
    }
}