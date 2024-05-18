package com.dashdrop.ui.components

import android.util.Log
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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.data.model.Item
import com.dashdrop.data.utils.UiState
import com.dashdrop.fireStore.addCartinFireBase
import com.dashdrop.fireStore.changeFav
import com.dashdrop.fireStore.getFavList
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.ui.theme.bg

@Composable
fun ItemList(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    path: String?
) {

    var itemList = emptyList<Item>()

    when (val itemData = homeViewModel.itemData.collectAsState().value) {
        is UiState.Error -> {
            Image(
                imageVector = Icons.Filled.Error, contentDescription = null,
                Modifier.size(100.dp)
            )
        }

        is UiState.Idle -> {
            homeViewModel.getAllItems(path!!, navController)
        }

        is UiState.Loading -> {
            CircularProgressIndicator()
        }

        is UiState.Success -> {
            itemList = itemData.data.toList()
            Log.d("mera_tag", "CategoryList: $itemList")
            Log.d("mera_tag", "ItemList: $itemList.size")
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2)
    ) {
        items(itemList) {
            Surface(
                shape = RoundedCornerShape(7.dp),
                modifier = Modifier
                    .padding(2.dp)
                    .clickable(onClick = {
                        navController.navigate(
                            "details/${it.item_name}/${it.item_price}/${it.item_detail}/${it.item_star}"
                        )
                    })
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp, 2.dp, 2.dp, 2.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(7.dp)
                    ) {
                        Box() {
                            Image(
                                modifier = Modifier
                                    .size(180.dp)
                                    .background(bg)
                                    .padding(3.dp),
                                painter = painterResource(id = R.drawable.veggiess),
                                contentDescription = null
                            )
                            IconButton(
                                onClick = {
                                    changeFav(it.item_id)
                                    getFavList()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.FavoriteBorder,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Absolute.SpaceAround
                    ) {
                        Column {
                            Text(
                                text = it.item_name,
                                color = Color.Black,
                                fontSize = 22.sp,
                                fontWeight = FontWeight(550)
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            //TODO: Star wala lana se app crash ho ja raha hai, error "AnimatedContentKt$AnimatedContent"
//                                    StarsRow(starCount = it.item_star.toInt(),22.dp)
                            Spacer(modifier = Modifier.height(3.dp))
                            Row {
                                Text(
                                    text = "₹", fontSize = 24.sp
                                )
                                Text(
                                    text = it.item_price, color = Color.Green, fontSize = 18.sp
                                )
                                Text(
                                    text = "/KG", fontSize = 18.sp
                                )
                            }

                        }
                        FAB(
                            onClick = {
                                addCartinFireBase(it.item_id,true)
                            },
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