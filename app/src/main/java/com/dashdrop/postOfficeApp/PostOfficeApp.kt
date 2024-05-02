package com.dashdrop.postOfficeApp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dashdrop.screens.*


@Composable
fun PostOfficeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = PostOfficeAppRouter.currentScreen, label = "") { currentState ->
            when(currentState.value){
                Screen.BillingScreen -> BillingScreen()
                Screen.CartScreen -> CartScreen()
                Screen.FavoriteScreen -> FavoriteScreen()
                Screen.HomeScreen -> HomeScreen()
                Screen.ProfileScreen -> ProfileScreen()
                Screen.SignInScreen -> SignInScreen()
                Screen.SignUpScreen -> SignUpScreen()
            }
        }
    }
}