package com.dashdrop.navigation

const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"
const val ROOT_GRAPH_ROUTE = "root"

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
}

//object DashDropAppRouter {
//
//    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignInScreen)
//
//    fun navigateTo(destination: Screen) {
//        currentScreen.value = destination
//    }
//
//}