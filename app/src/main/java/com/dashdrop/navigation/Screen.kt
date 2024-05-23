package com.dashdrop.navigation

sealed class Screen(val route: String) {
    object SignInScreen : Screen(route = "signin")
    object SignUpScreen : Screen(route = "signup")
    object HomeScreen : Screen(route = "home")
    object CategoryScreen : Screen(route = "category")
    object CartScreen : Screen(route = "cart")
    object ProfileScreen : Screen(route = "profile")
    object BillingScreen : Screen(route = "billing")
    object DetailsScreen :Screen(route = "details")
    object FavouriteScreen : Screen(route = "favourite")
    object AddressFormScreen : Screen(route = "address")
}