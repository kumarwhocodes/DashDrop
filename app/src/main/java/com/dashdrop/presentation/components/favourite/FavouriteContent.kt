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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dashdrop.data.model.Favourite
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.components.core.HeadingText
import com.dashdrop.presentation.components.home.ErrorComponent
import com.dashdrop.presentation.viewmodels.FavViewModel
import com.dashdrop.ui.theme.rubikBoldStyle

@Composable
fun FavouriteList(
    favViewModel: FavViewModel = hiltViewModel()
) {
    val favData = favViewModel.favData.collectAsState().value
    var favList by remember { mutableStateOf(emptyList<Favourite>()) }

    when (favData) {
        is UiState.Error -> {
            ErrorComponent(errorMessage = "Failed to load Favourites")
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

            if (favList.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    modifier = Modifier.background(Color.Transparent)
                ) {
                    items(favList) { item ->
                        FavouriteItem(item = item)
                    }
                }
            }else {
                // Show a message indicating that the favourite is empty
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    HeadingText(
                        value = "Your favourite is empty",
                        size = 20.sp,
                        color = Color.Red,
                        font = rubikBoldStyle
                    )
                }
            }

        }
    }
}