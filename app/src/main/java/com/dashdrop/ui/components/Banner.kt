package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.ui.theme.bg

@Composable
fun ItemBanner(
    image: Painter, contentDescription: String? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(10.dp)
            .height(150.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = bg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        listOf(Color.Yellow, bg), radius = 700f
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(220.dp)
                    .background(Color.Transparent)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 50.dp, top = 20.dp),
                    text = contentDescription!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 4,
                    minLines = 2
                )

                SecondaryButton(modifier = Modifier
                    .width(160.dp)
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp, end = 0.dp, top = 30.dp, bottom = 10.dp),
                    onClick = {

                    }) {
                    Text(
                        text = "Shop Now!", fontSize = 16.sp
                    )
                }


            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(150.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = image,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(Color.Transparent)
                )
            }
        }


    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    ItemBanner(
        image = painterResource(id = R.drawable.food),
        contentDescription = "Burgers starting at ₹199 only!!"
    )
}