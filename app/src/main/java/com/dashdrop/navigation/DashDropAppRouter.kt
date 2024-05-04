package com.dashdrop.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen() {
    object SignInScreen : Screen()
    object SignUpScreen : Screen()
    object HomeScreen : Screen()
    object FavoriteScreen : Screen()
    object CartScreen : Screen()
    object ProfileScreen : Screen()
    object BillingScreen : Screen()
}

object DashDropAppRouter {

    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignInScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

}