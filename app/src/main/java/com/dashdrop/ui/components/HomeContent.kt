package com.dashdrop.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.data.model.Category
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.viewmodels.HomeViewModel

@Composable
fun CategoryList(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {

    var categoryRowList = emptyList<Category>()

    when (val categoryData = homeViewModel.categoryData.collectAsState().value) {
        is UiState.Error -> {
            Image(
                imageVector = Icons.Filled.Error, contentDescription = null,
                Modifier.size(100.dp)
            )
        }

        is UiState.Idle -> {
            homeViewModel.getAllCategory()
        }

        is UiState.Loading -> {
            CircularProgressIndicator()
        }

        is UiState.Success -> {
            categoryRowList = categoryData.data.toList()
            Log.d("mera_tag", "CategoryList: $categoryRowList")

        }
    }

    LazyRow {
        items(categoryRowList) {
            Log.d("mera_tag", "HomeScreen: $categoryRowList}")
            CategoryButton(
                value = it.category_name,
                image = R.drawable.veggiess,
                onClick = {
//                    homeViewModel.getAllItems(it.category_name, navController)
                }
            )
        }
    }


}