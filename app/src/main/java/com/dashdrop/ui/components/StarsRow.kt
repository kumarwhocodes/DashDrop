package com.dashdrop.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dashdrop.R
import com.dashdrop.ui.theme.starColor
import kotlin.math.roundToInt

@Composable
fun StarsRow(
    starCount: Double,
    size: Dp
) {

    val stars = starCount.roundToInt()
    Box(){
        LazyRow() {
            items(5) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = stringResource(R.string.star),
                    modifier = Modifier
                        .size(size),
                    tint = starColor
                )
            }
        }
        LazyRow() {
            items(stars) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(R.string.star),
                    modifier = Modifier
                        .size(size),
                    tint = starColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {

    StarsRow(starCount = 4.0,53.dp)

}