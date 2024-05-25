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
import androidx.compose.material3.FloatingActionButton
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
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.dashdrop.R
import com.dashdrop.data.model.Item
import com.dashdrop.data.utils.UiState
import com.dashdrop.fireStore.addCartinFireBase
import com.dashdrop.fireStore.changeFav
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.TertiaryColor
import com.dashdrop.ui.theme.backgroundColor
import com.dashdrop.ui.theme.bg
import com.dashdrop.ui.theme.cardBackgroundColor
import com.dashdrop.ui.theme.cardIconBackgroundColor
import com.dashdrop.ui.theme.favBackgroundColor
import com.dashdrop.ui.theme.favIconBackgroundColor
import com.dashdrop.ui.theme.rubikSemiBoldStyle

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
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.background(Color.Transparent)
    ) {
        items(itemList) { item ->
            Surface(
                color = cardBackgroundColor,
                shape = RoundedCornerShape(7.dp),
                modifier = Modifier
                    .size(191.dp,219.dp)
                    .padding(2.dp)
                    .clickable(onClick = {
                        navController.navigate(
                            "details/${item.item_name}/${item.item_price}/${item.item_detail}/${item.item_star}/${item.item_category}"
                        )
                    })
            ) {
                Box{
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(7.dp)
                        ) {
                            Box {
                                Image(
                                    modifier = Modifier
                                        .size(183.dp,129.dp)
                                        .background(cardIconBackgroundColor)
                                        .padding(3.dp),
                                    painter = painterResource(id = R.drawable.strawberries),
                                    contentDescription = null
                                )
                                IconButton(
                                    onClick = {
                                        changeFav(item.item_id)
                                    },
                                    modifier = Modifier.align(Alignment.TopEnd),
                                    colors = androidx.compose.material3.IconButtonDefaults.iconButtonColors(
                                        containerColor = favBackgroundColor
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null,
                                        tint = favIconBackgroundColor
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = item.item_name,
                                    color = Color.Black,
                                    fontFamily = rubikSemiBoldStyle,
                                    fontSize = 22.sp
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Row {
                                    Text(
                                        text = "₹", fontSize = 18.sp , color = PrimaryColor
                                    )
                                    Text(
                                        text = item.item_price, color = PrimaryColor, fontSize = 18.sp
                                    )
                                    Text(
                                        text = "/KG", fontSize = 18.sp, color = TertiaryColor
                                    )
                                }
                            }
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            addCartinFireBase(item.item_id, true)
                        },
                        shape = RoundedCornerShape(7.dp, 0.dp, 7.dp, 0.dp),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(33.dp),
                        containerColor = PrimaryColor,
                        contentColor = Color.White
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Add to cart"
                        )
                    }
                }
            }
        }
    }



}