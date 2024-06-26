package com.dashdrop.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.ui.theme.rubikExtraBoldStyle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(5f).getInterpolation(it)
                })
        )
        delay(2000L)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(Screen.SignInScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                    saveState = true
                }
            }
        } else {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                    saveState = true
                }
            }
        }

    })


    Surface(
        modifier = Modifier
            .padding(16.dp, vertical = 225.dp),
            color = Color.White,
            shape = CircleShape,
            border = BorderStroke(
            width = 2.dp,
            color = Color.Black
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(256.dp)
            )
            Text(
                text = "Dash Drop",
                fontFamily = rubikExtraBoldStyle,
                fontSize = 45.sp
            )
        }

    }


}