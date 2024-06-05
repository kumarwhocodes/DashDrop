package com.dashdrop.presentation.components.cart

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dashdrop.data.model.Cart
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.components.core.CheckoutBottomBar
import com.dashdrop.presentation.viewmodels.CartViewModel
import com.dashdrop.ui.theme.backgroundColor

@Composable
fun CartList(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val cartState by cartViewModel.cartData.collectAsState()

    when (cartState) {
        is UiState.Error -> {
            Log.d("CartList", "Error: ${(cartState as UiState.Error).message}")
            Image(
                imageVector = Icons.Filled.Error, contentDescription = null,
                Modifier.size(100.dp)
            )
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
            val totalState by cartViewModel.total.collectAsState()

            Scaffold(
                bottomBar = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        CheckoutBottomBar(
                            price = totalState.toString(),
                            buttonAction = {
                                navController.navigate("billing/$totalState")
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
                            val subTotalState by cartViewModel.subtotal.collectAsState()
                            PricingCard(subTotal = subTotalState, total = totalState)
                        }
                    }
                }
            }
        }
    }
}