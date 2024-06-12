package com.dashdrop.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.data.model.Details
import com.dashdrop.data.repo.cart.addCartInFireBase
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.components.core.AddToCartBottomBar
import com.dashdrop.presentation.components.core.FAB
import com.dashdrop.presentation.components.core.HeadingText
import com.dashdrop.presentation.components.core.ScaffoldTop
import com.dashdrop.presentation.components.core.StarsRow
import com.dashdrop.presentation.components.home.DetailsImage
import com.dashdrop.presentation.components.home.ErrorComponent
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.TertiaryColor
import com.dashdrop.ui.theme.detailIconBackgroundColor
import com.dashdrop.ui.theme.rubikBoldStyle
import com.dashdrop.ui.theme.subtractBackgroundColor

@Composable
fun DetailsScreen(
    signInViewModel: SignInViewModel = viewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    itemId: String?,
    whenScreen: String?
) {

    var details: Details? = null

    when(val detailsData = homeViewModel.details.collectAsState().value){
        is UiState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is UiState.Idle -> {
            homeViewModel.getDetails(itemId!!)
        }
        is UiState.Success -> {
            details = detailsData.data
            Log.d("Details of the selected item", "Details $details")
        }
        is UiState.Error -> {
            ErrorComponent(errorMessage = "Failed to load Details")
        }
        else -> {}
    }

    if(details != null){
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
                        when(whenScreen){
                            "Category" -> navController.popBackStack("category/${details.itemCategory}", inclusive = false)
                            "Home" -> navController.popBackStack("home", inclusive = false)
                        }
                    },
                    contentColor = Color.Gray,
                    containerColor = detailIconBackgroundColor
                )
            },
            bottomBar = {
                AddToCartBottomBar(
                    addToCartButtonClicked = {
                        addCartInFireBase(
                            itemId = details.itemId,
                            operation = true,
                            cnt = quantity
                        )
                        quantity = 0
                    },
                    price = details.itemPrice
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
                        image = details.itemImage,
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
                            value = details.itemName,
                            size = 32.sp,
                            color = Color.Black,
                            font = rubikBoldStyle
                        )
                        Spacer(
                            modifier = Modifier.height(1.dp)
                        )
                        StarsRow(
                            starCount = details.itemStar.toInt(), size = 18.dp
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "₹", fontSize = 18.sp, color = PrimaryColor
                            )
                            Text(
                                text = details.itemPrice, color = PrimaryColor, fontSize = 18.sp
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
                                        if (quantity > 0)
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
                                value = details.itemDetail,
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


}