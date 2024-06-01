package com.dashdrop.presentation.components.favourite

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.data.model.Favourite
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.TertiaryColor
import com.dashdrop.ui.theme.cardBackgroundColor
import com.dashdrop.ui.theme.cardIconBackgroundColor
import com.dashdrop.ui.theme.rubikSemiBoldStyle

@Composable
fun FavouriteItem(item: Favourite) {
    Surface(
        color = cardBackgroundColor,
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier.padding(2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(5.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(7.dp)
            ) {
                Box {
                    Image(
                        modifier = Modifier
                            .size(183.dp,129.dp)
                            .background(cardIconBackgroundColor)
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
                        fontFamily = rubikSemiBoldStyle,
                        fontSize = 22.sp
                    )
//                    Spacer(modifier = Modifier.height(3.dp))
//                    StarsRow(starCount = item.item_star.toInt(), 25.dp)
                    Spacer(modifier = Modifier.height(3.dp))
                    Row {
                        Text(
                            text = "₹", fontSize = 18.sp , color = PrimaryColor
                        )
                        Text(
                            text = item.item_price, color = PrimaryColor, fontSize = 18.sp
                        )
                        Text(
                            text = "/KG", fontSize = 18.sp, color = TertiaryColor
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