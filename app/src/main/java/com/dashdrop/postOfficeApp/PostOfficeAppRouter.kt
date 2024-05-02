package com.dashdrop.postOfficeApp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object SignInScreen : Screen()
    object SignUpScreen : Screen()
    object HomeScreen : Screen()
    object FavoriteScreen : Screen()
    object CartScreen : Screen()
    object ProfileScreen : Screen()
    object BillingScreen : Screen()
}

object PostOfficeAppRouter{

    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignInScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }

}