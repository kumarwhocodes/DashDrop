package com.dashdrop.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dashdrop.presentation.screens.BillingScreen
import com.dashdrop.presentation.screens.CartScreen
import com.dashdrop.presentation.screens.CategoryScreen
import com.dashdrop.presentation.screens.DetailsScreen
import com.dashdrop.presentation.screens.FavouriteScreen
import com.dashdrop.presentation.screens.HomeScreen
import com.dashdrop.presentation.screens.ProfileScreen
import com.dashdrop.presentation.screens.SignInScreen
import com.dashdrop.presentation.screens.SignUpScreen
import com.dashdrop.presentation.viewmodels.SignInViewModel

@Composable
fun Navigation(navController: NavHostController) {
    val signInViewModel: SignInViewModel = viewModel()
    var initialScreen: String = "signin"
    signInViewModel.checkForActiveSession()

    if (signInViewModel.isUserLoggedIn.value == true) {
        initialScreen = "home"
    }

    val activity = (LocalContext.current as? Activity)
    NavHost(navController = navController, startDestination = initialScreen) {
        composable(route = "signin") {
            SignInScreen(navController = navController)
        }
        composable(route = "signup") {
            SignUpScreen(navController = navController)
        }
        composable(route = "home") {
            HomeScreen(navController = navController,
                onBackPressed = {
                    if (signInViewModel.isUserLoggedIn.value == true) {
                        activity?.finish()
                    } else {
                        navController.popBackStack()
                    }
                })
        }
        composable(
            route = "category/{items_category}",
            arguments = listOf(
                navArgument(name = "items_category"){
                    type = NavType.StringType
                }
            )
        ) {
            CategoryScreen(
                navController = navController,
                item_category = it.arguments?.getString("items_category")
            )
        }
        composable(
            route = "details/{itemIndex}",
            arguments = listOf(
                navArgument(name = "itemIndex"){
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt("itemIndex")?.let { it1 ->
                DetailsScreen(
                    navController = navController,
                    itemIndex = it1
                )
            }
        }
        composable(route = "favourite") {
            FavouriteScreen(navController = navController)
        }
        composable(route = "cart") {
            CartScreen(navController = navController)
        }
        composable(route = "profile") {
            ProfileScreen(navController = navController)
        }
        composable(route = "billing") {
            BillingScreen(navController = navController)
        }
    }
}