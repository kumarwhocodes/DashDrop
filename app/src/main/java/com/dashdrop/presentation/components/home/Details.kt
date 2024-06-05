package com.dashdrop.presentation.components.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dashdrop.ui.theme.detailIconBackgroundColor

@Composable
fun DetailsImage(
    image: String,
    imageDesc: String
) {
    AsyncImage(
        model = image,
        contentDescription = imageDesc,
        modifier = Modifier
            .size(430.dp,345.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(detailIconBackgroundColor)
            .padding(50.dp)
    )
    Log.d("image_url_detail",image)
}