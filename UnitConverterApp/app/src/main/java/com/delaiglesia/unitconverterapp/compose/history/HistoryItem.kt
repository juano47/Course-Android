package com.delaiglesia.unitconverterapp.compose.history

import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryItem(
    messagePart1: String,
    messagePart2: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    showDivider: Boolean,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = SpaceBetween
    ) {
        Column(modifier = modifier.padding(10.dp, 5.dp, 0.dp, 5.dp)) {
            Text(
                text = messagePart1,
                fontSize = 20.sp
            )
            Text(
                text = messagePart2,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        IconButton(onClick = { onClose() }) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }

    }
    if (showDivider) {
        Divider(color = Color.Gray, thickness = 0.5.dp)
    }
}
