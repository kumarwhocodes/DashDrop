package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.CartItem
import com.dashdrop.ui.components.CheckoutBottomBar
import com.dashdrop.ui.components.PricingCard
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.theme.bg

@Composable
fun CartScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
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
            CheckoutBottomBar {
                onCheckoutButtonClicked()
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())

        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CartItem(
                    image = painterResource(id = R.drawable.orange),
                    name = "Orange",
                    category = "Fruits",
                    price = "100"
                )
                CartItem(
                    image = painterResource(id = R.drawable.orange),
                    name = "Orange",
                    category = "Fruits",
                    price = "100"
                )
                CartItem(
                    image = painterResource(id = R.drawable.orange),
                    name = "Orange",
                    category = "Fruits",
                    price = "100"
                )
                Spacer(modifier = Modifier.height(10.dp))
                PricingCard()

            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CartScreen(navController = rememberNavController())
}