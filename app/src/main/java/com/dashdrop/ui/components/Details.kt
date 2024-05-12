package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.presentation.screens.DetailsScreen
import com.dashdrop.ui.theme.bg

@Composable
fun DetailsImage(
    image: Painter,
    imagedesc: String,
    size: Dp,
    color: Color
) {
    Image(
        painter = image,
        contentDescription = imagedesc,
        modifier = Modifier
            .fillMaxWidth()
            .size(size)
            .background(color)
    )
}

@Composable
fun DetailsName(
    name: String,
    money: String
) {
    Surface(
        modifier = Modifier.padding(10.dp)
    ){
        Column(){
            Text(
                text = "Veggies",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            StarsRow(starCount = 2, 30.dp)
            Spacer(modifier = Modifier.height(15.dp))
            Row {
                Text(
                    text = "150", color = Color.Green, fontSize = 25.sp
                )
                Text(
                    text = "/KG", fontSize = 25.sp
                )
            }
            Text(
                text = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(){
        DetailsImage(
            image = painterResource(R.drawable.veggiess),
            imagedesc = "Veggies",
            size = 280.dp,
            color = bg.copy(0.5f)
        )
        DetailsName(
            name = "Veggies",
            money = "150"
        )
    }
}