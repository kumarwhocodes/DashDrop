package com.dashdrop.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dashdrop.presentation.screens.BillingScreen
import com.dashdrop.presentation.screens.CartScreen
import com.dashdrop.presentation.screens.DetailsScreen
import com.dashdrop.presentation.screens.FavoriteScreen
import com.dashdrop.presentation.screens.HomeScreen
import com.dashdrop.presentation.screens.ProfileScreen
import com.dashdrop.presentation.screens.SignInScreen
import com.dashdrop.presentation.screens.SignUpScreen

//TODO: If user Logged In then open the HomePage

@Composable
fun DashDropApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = DashDropAppRouter.currentScreen, label = "") { currentState ->
            when(currentState.value){
                Screen.BillingScreen -> BillingScreen()
                Screen.CartScreen -> CartScreen()
                Screen.FavoriteScreen -> FavoriteScreen()
                Screen.HomeScreen -> HomeScreen()
                Screen.ProfileScreen -> ProfileScreen()
                Screen.SignInScreen -> SignInScreen()
                Screen.SignUpScreen -> SignUpScreen()
                Screen.DetailsScreen -> DetailsScreen()
            }
        }
    }
}