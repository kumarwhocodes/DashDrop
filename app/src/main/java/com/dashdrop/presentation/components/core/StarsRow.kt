package com.dashdrop.presentation.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dashdrop.ui.theme.PrimaryColor

@Composable
fun StarsRow(
    starCount: Int? = 0,
    size: Dp = 24.dp
) {

    Box{
        Row{
            repeat(5) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "empty star",
                    tint = PrimaryColor,
                    modifier = Modifier.size(size)
                )
            }
        }
        Row{
            if (starCount != null) {
                repeat(starCount) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "filled star",
                        tint = PrimaryColor,
                        modifier = Modifier.size(size)
                    )
                }
            }
        }
    }
}