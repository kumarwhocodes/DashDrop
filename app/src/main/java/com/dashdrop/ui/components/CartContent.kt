package com.dashdrop.ui.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dashdrop.data.model.Cart
import com.dashdrop.data.utils.UiState
import com.dashdrop.fireStore.addOrder
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.CartViewModel

@Composable
fun CartList(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartData = cartViewModel.cartData.collectAsState().value
    var cartList by remember { mutableStateOf(emptyList<Cart>()) }
    var total by remember { mutableStateOf(0.0) }

    when (cartData) {
        is UiState.Error -> {
            Log.d("CartList", "Error: ${cartData.message}")
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
            cartList = cartData.data.first
            total = cartData.data.second
            Log.d("CartList", "Cart items: $cartList")
        }
    }

    if (cartList.isNotEmpty()) {
        Scaffold(
            bottomBar = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    CheckoutBottomBar(
                        price = "" + (+total + 25.0).toString(),
                        buttonAction = {
//                            addOrder()
                            navController.navigate(Screen.BillingScreen.route)
                        },
                        buttonText = "Checkout"
                    )
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(cartList) { item ->
                    CartItem(item = item)
                }
                item {
                    PricingCard(subTotal = total)
                }
            }
        }
    }
}