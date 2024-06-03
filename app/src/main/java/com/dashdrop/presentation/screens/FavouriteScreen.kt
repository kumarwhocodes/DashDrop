package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.FavViewModel
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.components.core.BottomNavBar
import com.dashdrop.presentation.components.favourite.FavouriteList
import com.dashdrop.presentation.components.core.ScaffoldTop
import com.dashdrop.ui.theme.backgroundColor

@Composable
fun FavouriteScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    favViewModel: FavViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Favourites",
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
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            FavouriteList(navController = navController, favViewModel = favViewModel)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FavouriteScreen(
        navController = rememberNavController()
    )
}