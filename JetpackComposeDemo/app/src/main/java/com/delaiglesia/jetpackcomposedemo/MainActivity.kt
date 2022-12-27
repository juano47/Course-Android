package com.delaiglesia.jetpackcomposedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.delaiglesia.jetpackcomposedemo.compose.TvShowListItem
import com.example.composerecyclerview.data.TvShowList
import com.example.composerecyclerview.model.TvShow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplayTvShows {
                startActivity(InfoActivity.intentShowInfo(this, it))
            }
        }
    }
}

@Composable
private fun DisplayTvShows(selectedItem: (TvShow) -> Unit) {
    //remember is used to keep the state of the list of tv shows when the screen is rotated
    // or when the app is resumed from the background state to the foreground state
    val tvShows = remember { TvShowList.tvShows }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(
            items = tvShows,
            itemContent = {
                TvShowListItem(tvShow = it, selectedItem)
            }
        )
    }
}

