package com.delaiglesia.unitconverterapp.compose.history

import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delaiglesia.unitconverterapp.data.ConversionResult

@Composable
fun HistoryScreen(
    historyResults: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    removeResult: (ConversionResult) -> Unit,
    removeAllResults: () -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = SpaceBetween
        ) {
            Text(
                text = "History",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            //only show the button if there are results to remove
            if (historyResults.value.isNotEmpty()) {
                OutlinedButton(
                    onClick = { removeAllResults() }) {
                    Text(
                        text = "Clear all",
                        color = Color.Gray
                    )
                }
            }
        }
        HistoryList(
            historyResults,
            onCloseTask = { result -> removeResult(result) })
    }

}