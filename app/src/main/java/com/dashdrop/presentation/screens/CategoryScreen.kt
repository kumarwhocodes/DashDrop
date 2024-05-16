package com.dashdrop.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.fireStore.changeFav
import com.dashdrop.fireStore.getFavList
import com.dashdrop.fireStore.getcartList
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.FAB
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.theme.bg

@Composable
fun CategoryScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    item_category: String?
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            if (item_category != null) {
                ScaffoldTop(toolbarTitle = item_category,
                    logOutButtonClicked = {
                        signInViewModel.logout(navController)
                    },
                    navigationIconClicked = {
                        navController.popBackStack(Screen.HomeScreen.route, inclusive = false)
                    })
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(count = 2)
//            ) {
//                items(itemList){
//                    Surface(
//                        shape = RoundedCornerShape(7.dp),
//                        modifier = Modifier
//                            .padding(2.dp)
//                            .clickable(onClick = {
//                                navController.navigate("details/${it.index}")
//                            })
//                    ) {
//                        Column(
//                            verticalArrangement = Arrangement.Center,
//                            modifier = Modifier.padding(2.dp, 2.dp, 2.dp, 2.dp)
//                        ) {
//                            Surface(
//                                shape = RoundedCornerShape(7.dp)
//                            ) {
//                                Box(){
//                                    Image(
//                                        modifier = Modifier
//                                            .size(180.dp)
//                                            .background(bg)
//                                            .padding(3.dp),
//                                        painter = painterResource(id = R.drawable.veggiess),
//                                        contentDescription = null
//                                    )
//                                    IconButton(
//                                        onClick = {
//                                            changeFav(it.item_name)
//                                            getFavList()
//                                        }
//                                    ) {
//                                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
//                                    }
//                                }
//                            }
//                            Spacer(modifier = Modifier.height(8.dp))
//                            Row(
//                                verticalAlignment = Alignment.Bottom,
//                                horizontalArrangement = Arrangement.Absolute.SpaceAround
//                            ) {
//                                Column {
//                                    Text(
//                                        text = it.item_name,
//                                        color = Color.Black,
//                                        fontSize = 22.sp,
//                                        fontWeight = FontWeight(550)
//                                    )
//                                    Spacer(modifier = Modifier.height(3.dp))
//                                    //TODO: Star wala lana se app crash ho ja raha hai, error "AnimatedContentKt$AnimatedContent"
////                                    StarsRow(starCount = it.item_star.toInt(),22.dp)
//                                    Spacer(modifier = Modifier.height(3.dp))
//                                    Row {
//                                        Text(
//                                            text = "₹", fontSize = 24.sp
//                                        )
//                                        Text(
//                                            text = it.item_price, color = Color.Green, fontSize = 18.sp
//                                        )
//                                        Text(
//                                            text = "/KG", fontSize = 18.sp
//                                        )
//                                    }
//
//                                }
//                                FAB(
//                                    onClick = {
//                                        addcart(it.item_id,it.item_category)
//                                        for(i in cartListID){
//                                            getcartList(i.item_id)
//                                        }
//                                    },
//                                    modifier = Modifier
//                                        .padding(30.dp, 0.dp, 0.dp, 5.dp)
//                                        .size(33.dp),
//                                    icon = painterResource(id = R.drawable.add)
//                                )
//                            }
//
//                        }
//                    }
//                }
//            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CategoryScreen(
        navController = rememberNavController(),
        item_category = "items_category"
    )
}