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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.data.repo.cart.addCartInFireBase
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.components.core.AddToCartBottomBar
import com.dashdrop.presentation.components.core.FAB
import com.dashdrop.presentation.components.core.HeadingText
import com.dashdrop.presentation.components.core.ScaffoldTop
import com.dashdrop.presentation.components.core.StarsRow
import com.dashdrop.presentation.components.home.DetailsImage
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.TertiaryColor
import com.dashdrop.ui.theme.detailIconBackgroundColor
import com.dashdrop.ui.theme.rubikBoldStyle
import com.dashdrop.ui.theme.subtractBackgroundColor

@Composable
fun DetailsScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    itemName: String?,
    itemPrice: String?,
    itemDetail: String?,
    itemStar: String?,
    itemCategory: String?,
    image: String?,
    itemId: String?
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
                    navController.popBackStack("category/${itemCategory}", inclusive = false)
                },
                contentColor = Color.Gray,
                containerColor = detailIconBackgroundColor
            )
        },
        bottomBar = {
            AddToCartBottomBar(
                addToCartButtonClicked = {
                    addCartInFireBase(itemId = itemId!!, operation = true,cnt = quantity)
                    quantity = 0
                },
                price = itemPrice!!
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                DetailsImage(
                    image = image!!,
                    imageDesc = "Veggies"
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    HeadingText(
                        value = itemName!!,
                        size = 32.sp,
                        color = Color.Black,
                        font = rubikBoldStyle
                    )
                    Spacer(
                        modifier = Modifier.height(1.dp)
                    )
                    StarsRow(
                        starCount = itemStar?.toInt(), size = 18.dp
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "₹", fontSize = 18.sp , color = PrimaryColor
                        )
                        Text(
                            text = itemPrice!!, color = PrimaryColor, fontSize = 18.sp
                        )
                        Text(
                            text = "/KG", fontSize = 18.sp, color = TertiaryColor
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.End
                        ) {

                            FAB(
                                onClick = {
                                      if(quantity > 0)
                                          quantity--
                                },
                                icon = painterResource(id = R.drawable.minus),
                                modifier = Modifier
                                    .size(24.dp),
                                containerColor = subtractBackgroundColor
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(8.dp)
                            )
                            Text(
                                text = "$quantity",
                                fontSize = 24.sp,
                                color = Color.Black.copy(0.5f)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(8.dp)
                            )
                            FAB(
                                onClick = { quantity++ },
                                icon = painterResource(id = R.drawable.add),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                    HeadingText(
                        value = "Product Details",
                        size = 24.sp,
                        color = Color.Black,
                        font = rubikBoldStyle
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        HeadingText(
                            value = itemDetail!!,
                            size = 16.sp,
                            color = Color.Black.copy(0.5f),
                            textAlign = TextAlign.Left,
                            lineHeight = 20.sp,
                            font = rubikBoldStyle
                        )
                    }


                }


            }

        }
    }


}