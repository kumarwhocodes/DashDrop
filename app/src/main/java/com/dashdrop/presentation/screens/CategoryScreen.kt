package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.components.home.ItemList
import com.dashdrop.presentation.components.core.ScaffoldTop
import com.dashdrop.ui.theme.backgroundColor

@Composable
fun CategoryScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    itemCategory: String?
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            if (itemCategory != null) {
                ScaffoldTop(toolbarTitle = itemCategory,
                    logOutButtonClicked = {
                        signInViewModel.logout(navController)
                    },
                    navigationIconClicked = {
                        navController.popBackStack(Screen.HomeScreen.route, inclusive = false)
                    })
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(8.dp),
        )
        {
            ItemList(navController = navController,path = itemCategory)
        }

    }
}