package com.dashdrop.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dashdrop.presentation.screens.CartScreen
import com.dashdrop.presentation.screens.FavouriteScreen
import com.dashdrop.presentation.screens.ProfileScreen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.viewmodels.SignUpViewModel

//TODO: If user Logged In then open the HomePage

@Composable
fun DashDropNavGraph(
    signInViewModel: SignInViewModel,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController = rememberNavController(),
    isUserLoggedIn: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        val initialRoute =
            if (isUserLoggedIn) HOME_GRAPH_ROUTE
            else AUTH_GRAPH_ROUTE

        NavHost(
            navController = navController,
            startDestination = initialRoute,
            route = ROOT_GRAPH_ROUTE
        ) {
            homeNavGraph(navController, signInViewModel)
            authNavGraph(navController, signInViewModel, signUpViewModel)

            composable(
                route = Screen.FavouriteScreen.route
            ) {
                FavouriteScreen(
                    navController = navController
                )
            }

            composable(
                route = Screen.CartScreen.route
            ) {
                CartScreen(
                    navController = navController
                )
            }

            composable(
                route = Screen.ProfileScreen.route
            ) {
                ProfileScreen(
                    navController = navController
                )
            }


        }


//        Crossfade(targetState = DashDropAppRouter.currentScreen, label = "") { currentState ->
//            when(currentState.value){
//                Screen.BillingScreen -> BillingScreen()
//                Screen.CartScreen -> CartScreen()
//                Screen.CategoryScreen -> CategoryScreen()
//                Screen.HomeScreen -> HomeScreen()
//                Screen.ProfileScreen -> ProfileScreen()
//                Screen.SignInScreen -> SignInScreen()
//                Screen.SignUpScreen -> SignUpScreen()
//                Screen.DetailsScreen -> DetailsScreen()
//                Screen.FavouriteScreen -> FavouriteScreen()
//            }
//        }
    }
}