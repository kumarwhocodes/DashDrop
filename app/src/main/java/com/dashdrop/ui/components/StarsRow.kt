package com.dashdrop.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dashdrop.R
import com.dashdrop.ui.theme.starColor
import kotlin.math.roundToInt

@Composable
fun StarsRow(
    modifier: Modifier = Modifier,
    starCount: Double
) {

    val stars = starCount.roundToInt()

    LazyRow(
        modifier = Modifier
            .padding(0.dp)
    ) {
        items(stars) {

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(R.string.star),
                modifier = modifier
                    .size(8.dp),
                tint = starColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {

    StarsRow(starCount = 5.0)

}