package com.dashdrop.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dashdrop.presentation.screens.SignInScreen
import com.dashdrop.presentation.screens.SignUpScreen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.viewmodels.SignUpViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    signInViewModel: SignInViewModel,
    signUpViewModel: SignUpViewModel
){
    navigation(
        startDestination = Screen.SignInScreen.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.SignInScreen.route
        ){
            SignInScreen(
                navController = navController,
                signInViewModel = signInViewModel
            )
        }

        composable(
            route = Screen.SignUpScreen.route
        ){
            SignUpScreen(
                navController = navController,
                signUpViewModel = signUpViewModel
            )

        }
    }
}