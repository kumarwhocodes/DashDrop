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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.data.model.Cart
import com.dashdrop.data.model.Category
import com.dashdrop.data.utils.UiState
import com.dashdrop.fireStore.CartItems
import com.dashdrop.fireStore.addCartinFireBase
import com.dashdrop.presentation.viewmodels.CartViewModel
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.ui.theme.bg

@Composable
fun CartList(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartData = cartViewModel.cartData.collectAsState().value
    var cartList by remember { mutableStateOf(emptyList<Cart>()) }

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
            cartList = cartData.data
            Log.d("CartList", "Cart items: $cartList")
        }
    }

    if (cartList.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(cartList) { item ->
                CartItem(item = item)
            }
            item {
                PricingCard()
            }
        }
    }
}