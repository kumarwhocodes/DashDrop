package com.dashdrop.postOfficeApp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dashdrop.screens.billingScreen
import com.dashdrop.screens.cartScreen
import com.dashdrop.screens.favoriteScreen
import com.dashdrop.screens.homeScreen
import com.dashdrop.screens.profileScreen
import com.dashdrop.screens.signInScreen
import com.dashdrop.screens.signUpScreen

@Composable
fun PostOfficeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = PostOfficeAppRouter.currentScreen, label = "") { currentState ->
            when(currentState.value){
                Screen.BillingScreen -> billingScreen()
                Screen.CartScreen -> cartScreen()
                Screen.FavoriteScreen -> favoriteScreen()
                Screen.HomeScreen -> homeScreen()
                Screen.ProfileScreen -> profileScreen()
                Screen.SignInScreen -> signInScreen()
                Screen.SignUpScreen -> signUpScreen()
            }
        }
    }
}