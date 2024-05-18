package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.data.model.Favourite
import com.dashdrop.ui.theme.bg

@Composable
fun FavouriteItem(item: Favourite) {
    Surface(
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier.padding(2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(7.dp)
            ) {
                Box {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .background(bg)
                            .padding(3.dp),
                        painter = painterResource(id = R.drawable.veggiess),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Text(
                        text = item.item_name,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight(550)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Row {
                        Text(
                            text = "₹", fontSize = 24.sp
                        )
                        Text(
                            text = item.item_price, color = Color.Green, fontSize = 18.sp
                        )
                        Text(
                            text = "/KG", fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FavouriteItem(item =
        Favourite(
            item_name = "Tomato",
            item_price = "25",
            item_star = "3"
        )
    )
}