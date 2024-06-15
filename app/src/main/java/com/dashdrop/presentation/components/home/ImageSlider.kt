package com.dashdrop.presentation.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dashdrop.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

val images = listOf(
    R.drawable.banner1,
    R.drawable.banner2,
    R.drawable.banner3
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSliderWithIndicator(
    images: List<Int>
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            key = { images[it] },
            modifier = Modifier
                .fillMaxWidth()
        ) { index ->
            Image(
                painter = painterResource(id = images[index]), contentDescription = null,
                modifier = Modifier
                    .size(width = 390.dp, height = 180.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            activeColor = Color.White,
            inactiveColor = Color.Black.copy(0.5f),
            indicatorHeight = 10.dp,
            indicatorWidth = 10.dp,
            spacing = 5.dp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    ImageSliderWithIndicator(images = images)
}
