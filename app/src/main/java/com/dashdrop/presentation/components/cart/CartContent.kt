package com.dashdrop.presentation.components.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dashdrop.data.model.Cart
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.components.core.CheckoutBottomBar
import com.dashdrop.presentation.components.core.HeadingText
import com.dashdrop.presentation.components.home.ErrorComponent
import com.dashdrop.presentation.viewmodels.CartViewModel
import com.dashdrop.ui.theme.backgroundColor
import com.dashdrop.ui.theme.rubikBoldStyle

@Composable
fun CartList(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartState by cartViewModel.cartData.collectAsState()
    val subtotalState by cartViewModel.subtotal.collectAsState()
    val totalState by cartViewModel.total.collectAsState()

    when (cartState) {
        is UiState.Error -> {
            ErrorComponent(errorMessage = "Failed to load Cart")
        }

        is UiState.Idle -> {
            cartViewModel.getAllCart()
        }

        is UiState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val (cartList, subtotal) = (cartState as UiState.Success<Pair<ArrayList<Cart>, Double>>).data

            if (cartList.isNotEmpty()) {
                Scaffold(
                    bottomBar = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            val total = if (totalState == 0.0) subtotal + 25 else totalState
                            CheckoutBottomBar(
                                price = total.toString(),
                                buttonAction = {
                                    navController.navigate("billing/$total")
                                },
                                buttonText = "Checkout"
                            )
                        }
                    }
                ) {
                    Surface(
                        color = backgroundColor
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            items(cartList) { item ->
                                CartItem(item = item, navController = navController, cartViewModel = cartViewModel)
                            }
                            item {
                                PricingCard(
                                    subTotal = if (subtotalState == 0.0) subtotal else subtotalState,
                                    total = if (totalState == 0.0) subtotal + 25 else totalState
                                )
                            }
                        }
                    }
                }
            } else {
                // Show a message indicating that the cart is empty
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    HeadingText(
                        value = "Your cart is empty",
                        size = 20.sp,
                        color = Color.Red,
                        font = rubikBoldStyle
                    )
                }
            }
        }
    }
}
