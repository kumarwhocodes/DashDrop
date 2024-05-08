package com.dashdrop.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dashdrop.presentation.screens.CategoryScreen
import com.dashdrop.presentation.screens.DetailsScreen
import com.dashdrop.presentation.screens.HomeScreen
import com.dashdrop.presentation.viewmodels.SignInViewModel

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    signInViewModel: SignInViewModel
) {
    navigation(
        startDestination = Screen.HomeScreen.route,
        route = HOME_GRAPH_ROUTE
    ) {

        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.CategoryScreen.route
        ) {
            CategoryScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.DetailsScreen.route
        ) {
            DetailsScreen(
                navController = navController
            )
        }
    }
}