package com.dashdrop.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.viewmodels.getItemDetails
import com.dashdrop.presentation.viewmodels.getItemList
import com.dashdrop.presentation.viewmodels.itemDetailsList
import com.dashdrop.presentation.viewmodels.itemList
import com.dashdrop.ui.components.FAB
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.components.ItemButton
import com.dashdrop.ui.components.StarsRow
import com.dashdrop.ui.theme.bg
import com.dashdrop.ui.theme.starColor

@Composable
fun CategoryScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Veggies",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    navController.popBackStack(Screen.HomeScreen.route, inclusive = false)
                })
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            Button(onClick = {
                Log.d("size", itemDetailsList.size.toString())
                navController.navigate(Screen.DetailsScreen.route)
            }) {
                Text("Click here to go in Details Screen")
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2)
            ) {
                items(itemList){
                    Surface(
                        shape = RoundedCornerShape(7.dp),
                        modifier = Modifier.padding(2.dp)
                            .clickable(onClick = {
                                getItemDetails(it.item_id)
                            })
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(2.dp, 2.dp, 2.dp, 2.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(7.dp)
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(180.dp)
                                        .background(bg)
                                        .padding(3.dp),
                                    painter = painterResource(id = R.drawable.veggiess),
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Absolute.SpaceAround
                            ) {
                                Column {
                                    Text(
                                        text = it.item_name,
                                        color = Color.Black.copy(),
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(550)
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    //TODO: Star wala lana se app crash ho ja raha hai, error "AnimatedContentKt$AnimatedContent"
//                                    StarsRow(starCount = it.item_star.toInt(),22.dp)
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Row {
                                        Text(
                                            text = it.item_price, color = Color.Green, fontSize = 18.sp
                                        )
                                        Text(
                                            text = "/KG", fontSize = 18.sp
                                        )
                                    }

                                }
                                FAB(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .padding(30.dp, 0.dp, 0.dp, 5.dp)
                                        .size(33.dp),
                                    icon = painterResource(id = R.drawable.add)
                                )
                            }

                        }
                    }
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CategoryScreen(
        navController = rememberNavController()
    )
}