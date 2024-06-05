package com.dashdrop.presentation.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dashdrop.R
import com.dashdrop.ui.theme.PrimaryColor
import kotlinx.coroutines.delay

val images = listOf(
    R.drawable.banner1,
    R.drawable.banner2,
    R.drawable.banner3
)

@Composable
fun ImageSliderItem(
    image: Int
) {
    Image(
        painter = painterResource(id = image), contentDescription = null,
        modifier = Modifier
            .size(width = 390.dp, height = 180.dp)
            .clip(shape = RoundedCornerShape(20.dp))
    )
}

@Composable
fun Indicator(
    active: Boolean,
    onClick: () -> Unit
) {
    val color = if (active) PrimaryColor else Color.Black.copy(0.5f)
    val size = if (active) 10.dp else 10.dp

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = color)
            .size(size = size)
            .clickable(onClick = onClick)
    )
}

@Composable
fun ImageSliderWithIndicator(
    images: List<Int>
) {
    val currentIndex = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            currentIndex.intValue = (currentIndex.intValue + 1) % images.size
        }
    }
    ImageSliderItem(image = images[currentIndex.intValue])
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        images.forEachIndexed { index, _ ->
            Indicator(
                active = index == currentIndex.intValue,
                onClick = { currentIndex.intValue = index }
            )
            if (index < images.size - 1) {
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    ImageSliderWithIndicator(images = images)
}
