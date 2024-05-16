package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.AddToCartBottomBar
import com.dashdrop.ui.components.DetailsImage
import com.dashdrop.ui.components.FAB
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.theme.bg

@Composable
fun DetailsScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    addToCart: () -> Unit = {},
    itemIndex: Int
) {
    var quantity by remember {
        mutableIntStateOf(0)
    }
    Scaffold(modifier = Modifier,
        topBar = {
            ScaffoldTop(
                toolbarTitle = "Details",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    navController.popBackStack(Screen.CategoryScreen.route, inclusive = false)
                },
                containerColor = bg.copy(0.25f),
                contentColor = Color.Black.copy(0.75f),
                iconColor = bg
            )
        },
        bottomBar = {
            AddToCartBottomBar(
                addToCartButtonClicked = addToCart,
                price = "100"
//                price = itemList[itemIndex].item_price
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Top
//            ) {
//                DetailsImage(
//                    image = painterResource(R.drawable.veggiess),
//                    imagedesc = "Veggies",
//                    size = 280.dp,
//                    color = bg.copy(0.25f)
//                )
//                Spacer(
//                    modifier = Modifier.height(10.dp)
//                )
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(10.dp)
//                ) {
//                    HeadingText(
//                        modifier = Modifier,
//                        value = itemList[itemIndex].item_name,
//                        size = 32.sp,
//                        weight = FontWeight.Bold,
//                        color = Color.Black
//                    )
////                    Spacer(
////                        modifier = Modifier.height(10.dp)
////                    )
////                    StarsRow(
////                        starCount = itemDetailsList[0].item_star.toInt(), size = 24.dp
////                    )
//                    Spacer(
//                        modifier = Modifier.height(10.dp)
//                    )
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        Text(
//                            text = "₹", fontSize = 24.sp
//                        )
//                        Text(
//                            text = itemList[itemIndex].item_price, color = Color.Green, fontSize = 24.sp
//                        )
//                        Text(
//                            text = "/KG", fontSize = 24.sp
//                        )
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 4.dp),
//                            horizontalArrangement = Arrangement.End
//                        ) {
//
//                            FAB(
//                                onClick = { quantity-- },
//                                icon = painterResource(id = R.drawable.minus),
//                                modifier = Modifier
//                                    .size(24.dp)
//                            )
//                            Spacer(
//                                modifier = Modifier
//                                    .width(8.dp)
//                            )
//                            Text(
//                                text = "$quantity KG",
//                                fontSize = 24.sp,
//                                color = Color.Black.copy(0.5f)
//                            )
//                            Spacer(
//                                modifier = Modifier
//                                    .width(8.dp)
//                            )
//                            FAB(
//                                onClick = { quantity++ },
//                                icon = painterResource(id = R.drawable.add),
//                                modifier = Modifier
//                                    .size(24.dp)
//                            )
//                        }
//                    }
//                    Spacer(
//                        modifier = Modifier.height(24.dp)
//                    )
//                    HeadingText(
//                        modifier = Modifier,
//                        value = "Product Details",
//                        size = 24.sp,
//                        weight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//
//                    Spacer(
//                        modifier = Modifier.height(8.dp)
//                    )
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .verticalScroll(
//                                rememberScrollState()
//                            )
//                    ) {
//                        HeadingText(
//                            modifier = Modifier,
//                            value = itemList[itemIndex].item_detail,
//                            size = 16.sp,
//                            textAlign = TextAlign.Left,
//                            weight = FontWeight.Normal,
//                            color = Color.Black.copy(0.5f),
//                            lineHeight = 20.sp
//                        )
//                    }
//
//
//                }
//
//
//            }

        }
    }


}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    DetailsScreen(
        navController = rememberNavController(),
        itemIndex = 0
    )
}