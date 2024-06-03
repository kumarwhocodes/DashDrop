package com.dashdrop.presentation.components.favourite

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dashdrop.data.model.Favourite
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.viewmodels.FavViewModel

@Composable
fun FavouriteList(
    navController: NavController,
    favViewModel: FavViewModel = hiltViewModel()
) {
    val favData = favViewModel.favData.collectAsState().value
    var favList by remember { mutableStateOf(emptyList<Favourite>()) }

    when (favData) {
        is UiState.Error -> {
            Log.d("FavouriteList", "Error: ${favData.message}")
            Image(
                imageVector = Icons.Filled.Error, contentDescription = null,
                modifier = Modifier
            )
        }

        is UiState.Idle -> {
            favViewModel.getAllFav()
        }

        is UiState.Loading -> {
            Box(
                Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            favList = favData.data
            Log.d("FavouriteList", "Favourite items: $favList")
        }
    }

    if (favList.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            modifier = Modifier.background(Color.Transparent)
        ) {
            items(favList) { item ->
                FavouriteItem(item = item)
            }
        }
    }
}