package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.navigation.Screen
import com.dashdrop.ui.theme.bg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateTo: (Screen) -> Unit) {

    LaunchedEffect(key1 = Unit) {
        delay(1000)
        navigateTo(Screen.SignUpScreen)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Image(
//                painter = painterResource(),
//                contentDescription = "null"
//            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "Splash Screen",
                fontSize = 26.sp,
                color = Color.Black
            )
        }
    }

}