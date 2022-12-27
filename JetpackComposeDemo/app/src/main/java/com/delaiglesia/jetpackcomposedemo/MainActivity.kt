package com.delaiglesia.jetpackcomposedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delaiglesia.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //ScrollableColumn()
            LazyColumnDemo {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//basic ListView example, using a Column and a Divider to separate each item in the list
//it is not efficient, because it will create all the items in the list, even if they are not visible
//it is better to use LazyColumn, which will only create the items that are visible
@Composable
fun ScrollableColumn() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for (i in 1..100) {
            Text(
                text = "Username $i",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

//LazyColumn is more efficient, because it will only create the items that are visible
@Composable
fun LazyColumnDemo(selectedItem: (String) -> Unit) {
    LazyColumn {
        items(100) {
            Text(
                text = "Username $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { selectedItem("Username $it") }
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeDemoTheme {
        ScrollableColumn()
    }
}
