package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.fireStore.addCartinFireBase
import com.dashdrop.ui.theme.DashDropTheme
import com.dashdrop.ui.theme.TertiaryColor
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.bg
import com.dashdrop.ui.theme.cardBackgroundColor
import com.dashdrop.ui.theme.cardIconBackgroundColor
import com.dashdrop.ui.theme.rubikSemiBoldStyle
import com.google.android.play.integrity.internal.i

@Preview()
@Composable
private fun ButtonsPreview() {
    Column {
        DashDropTheme {
            CategoryButton(
                value = "Veggies",
                image = R.drawable.veggiess,
                imageDesc = "veggies",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(30.dp))

            ItemButton(
                value = "Veggies",
                image = painterResource(id = R.drawable.veggiess),
                imageDesc = "veggies",
                price = "$2.99",
                icon = painterResource(id = R.drawable.add),
                item_id = "d"
            )

            Spacer(modifier = Modifier.height(30.dp))

            CartButton(
                value = "Veggies",
                image = painterResource(id = R.drawable.veggiess),
                imageDesc = "veggies",
                price = "$2.99",
                startCount = 2
            )
        }
    }
}

@Composable
fun CategoryButton(
    value: String, image: Int, imageDesc: String?=null,
    onClick: ()-> Unit
) {
    Surface(
        shape = RoundedCornerShape(50.dp, 50.dp, 20.dp, 20.dp),
        modifier = Modifier
            .padding(2.dp)
            .clickable(onClick = onClick),
        color = cardBackgroundColor
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.clip(CircleShape)
            ) {
                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .background(cardIconBackgroundColor)
                        .padding(3.dp),
                    painter = painterResource(id = image),
                    contentDescription = imageDesc
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                color = Color.Black.copy(0.8f),
                fontSize = 13.sp,
                fontWeight = FontWeight(490)
            )
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}

@Composable
fun ItemButton(
    value: String,
    image: Painter,
    imageDesc: String? = null,
    price: String,
    icon: Painter,
    item_id: String
) {
    Surface(
        color = cardBackgroundColor,
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .size(191.dp,219.dp)
            .padding(2.dp)
            .clickable(onClick = {

            })
    ) {
        Box {
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
                                .size(183.dp, 129.dp)
                                .background(cardIconBackgroundColor)
                                .padding(3.dp),
                            painter = painterResource(id = R.drawable.strawberries),
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = value,
                            color = Color.Black,
                            fontFamily = rubikSemiBoldStyle,
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Row {
                            Text(
                                text = "₹", fontSize = 18.sp, color = PrimaryColor
                            )
                            Text(
                                text = price, color = PrimaryColor, fontSize = 18.sp
                            )
                            Text(
                                text = "/KG", fontSize = 18.sp, color = TertiaryColor
                            )
                        }
                    }
                }
            }
            FloatingActionButton(
                onClick = {
                    addCartinFireBase(itemId = item_id, true)
                },
                shape = RoundedCornerShape(7.dp, 0.dp, 7.dp, 0.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(33.dp),
                containerColor = PrimaryColor,
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add to cart"
                )
            }
        }
    }
}



@Composable
fun CartButton(
    value: String,
    image: Painter,
    imageDesc: String?=null,
    price: String,
    startCount: Int
) {
    Surface(
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier.padding(2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp, 2.dp, 2.dp, 2.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(7.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(180.dp)
                        .background(bg)
                        .padding(3.dp),
                    painter = image,
                    contentDescription = imageDesc
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Column {
                    Text(
                        text = value,
                        color = Color.Black.copy(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight(550)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    StarsRow(starCount = startCount,22.dp)
                    Spacer(modifier = Modifier.height(3.dp))
                    Row {
                        Text(
                            text = price, color = Color.Green, fontSize = 18.sp
                        )
                        Text(
                            text = "/KG", fontSize = 18.sp
                        )
                    }

                }
                FAB(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(30.dp, 0.dp, 0.dp, 5.dp)
                        .size(33.dp),
                    icon = painterResource(id = R.drawable.add)
                )
            }

        }
    }
}