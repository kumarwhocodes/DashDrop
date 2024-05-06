package com.dashdrop.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.ui.components.StarsRow
import com.dashdrop.ui.theme.bg
import java.nio.file.WatchEvent

@Composable
fun DetailsScreen() {
    Surface() {
        Column {
            Image(
                painter = painterResource(R.drawable.veggiess),
                contentDescription = "Veggies",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(280.dp)
                    .background(bg.copy(0.5f))
            )
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
                    StarsRow(starCount = 2.0, 30.dp)
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
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DetailsScreen()
}