package com.dashdrop.presentation.components.billing

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dashdrop.R
import com.dashdrop.ui.theme.PrimaryColor

@Composable
fun OrderPlacedAnimation() {

    val compositionTick by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.order))

    val textAlpha = remember { Animatable(0f) }
    val textScale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        textAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 0)
        )
        textScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 0)
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = compositionTick,
            modifier = Modifier.size(350.dp)
        )
        Text(
            text = "ORDER PLACED",
            fontWeight = FontWeight.Bold,
            color = PrimaryColor.copy(alpha = textAlpha.value),
            fontSize = (32.sp * textScale.value)
        )
    }

}

@Preview
@Composable
private fun Preview() {
    OrderPlacedAnimation()
}