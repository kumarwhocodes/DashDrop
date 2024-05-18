package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.fireStore.addCartinFireBase
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.CartViewModel
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.CartItem
import com.dashdrop.ui.components.CartList
import com.dashdrop.ui.components.CheckoutBottomBar
import com.dashdrop.ui.components.PricingCard
import com.dashdrop.ui.components.ScaffoldTop

@Composable
fun CartScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
    onCheckoutButtonClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Cart",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                    }
                })
        },
        bottomBar = {
            Column{
                PricingCard()
                CheckoutBottomBar {
                    onCheckoutButtonClicked()
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CartList(navController = navController,cartViewModel = cartViewModel)

            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CartScreen(navController = rememberNavController())
}