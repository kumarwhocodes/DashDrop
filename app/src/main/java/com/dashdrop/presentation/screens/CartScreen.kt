package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.AddToCartBottomBar
import com.dashdrop.ui.components.BottomNavBar
import com.dashdrop.ui.components.CheckoutBottomBar
import com.dashdrop.ui.components.ItemButton
import com.dashdrop.ui.components.ScaffoldTop

@Composable
fun CartScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    onCheckoutButtonClicked: () -> Unit={}
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Cart",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    navController.navigate(Screen.HomeScreen.route){
                        popUpTo(Screen.HomeScreen.route){inclusive = true}
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
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(count = 2)) {
                items(5){
                    ItemButton(
                        value = "Veggies",
                        image = painterResource(id = R.drawable.veggiess),
                        price = "150",
                        startCount = 2,
                        icon = painterResource(id = R.drawable.minus)
                    )
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CartScreen(navController = rememberNavController())
}