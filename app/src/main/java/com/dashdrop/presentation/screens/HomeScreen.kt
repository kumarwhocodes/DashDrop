package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.components.core.BottomNavBar
import com.dashdrop.presentation.components.home.CategoryList
import com.dashdrop.presentation.components.core.ScaffoldTop
import com.dashdrop.ui.theme.backgroundColor

@Composable
fun HomeScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    onBackPressed: () -> Unit = {},
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Home Screen",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    onBackPressed()
                })
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CategoryList(navController = navController, homeViewModel = homeViewModel)
            }
        }

    }


}