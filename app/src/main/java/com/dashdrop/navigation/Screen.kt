package com.dashdrop.navigation

sealed class Screen(val route: String) {
    data object SplashScreen : Screen(route = "splash")
    data object SignInScreen : Screen(route = "signin")
    data object SignUpScreen : Screen(route = "signup")
    data object HomeScreen : Screen(route = "home")
    data object CartScreen : Screen(route = "cart")
    data object ProfileScreen : Screen(route = "profile")
    data object FavouriteScreen : Screen(route = "favourite")
}