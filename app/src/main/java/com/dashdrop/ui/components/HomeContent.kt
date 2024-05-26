package com.dashdrop.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.data.model.Category
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.ui.theme.rubikBoldStyle

@Composable
fun CategoryList(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

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
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            categoryRowList = categoryData.data.toList()
            Log.d("mera_tag", "CategoryList: $categoryRowList")

        }
    }

    if(categoryRowList.isNotEmpty()){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            item{
                ImageSliderWithIndicator(images = images)

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
            }

            item{
                HeadingText(
                    modifier = Modifier,
                    value = "Categories",
                    size = 24.sp,
                    color = Color.Black,
                    font = rubikBoldStyle
                )
            }

            item{
                LazyRow {
                    items(categoryRowList) {
                        Log.d("mera_tag", "HomeScreen: $categoryRowList}")
                        CategoryButton(
                            value = it.category_name,
                            image = R.drawable.veggiess,
                            onClick = {
                                Log.d("mera_tag", "HomeScreen: ${it.category_name}")
                                navController.navigate("category/${it.category_name}")
                            }
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
            }

            item{
                HeadingText(
                    value = "Popular",
                    size = 24.sp,
                    font = rubikBoldStyle,
                    color = Color.Black,
                    modifier = Modifier
                )
            }

            item{
                Box(modifier = Modifier.height(400.dp)) {
                    LazyVerticalGrid(columns = GridCells.Fixed(count = 2)) {
                        items(5) {
                            ItemButton(
                                value = "Veggies",
                                image = painterResource(id = R.drawable.veggiess),
                                price = "150",
                                startCount = 2,
                                icon = painterResource(id = R.drawable.add)
                            )
                        }
                    }
                }
            }
        }
    }


}