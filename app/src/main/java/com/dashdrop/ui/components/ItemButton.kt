package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.ui.theme.DashDropTheme
import com.dashdrop.ui.theme.bg

@Preview
@Composable
private fun ButtonsPreview() {
    Column {
        DashDropTheme {
            CategoryButton(
                value = "Veggies",
                image = painterResource(id = R.drawable.veggiess),
                imageDesc = "veggies"
            )

            Spacer(modifier = Modifier.height(30.dp))

            ItemButton(
                value = "Veggies",
                image = painterResource(id = R.drawable.veggiess),
                imageDesc = "veggies",
                price = "$2.99",
                startCount = 2.0,
                icon = Icons.Filled.Add
            )

            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}

@Composable
fun CategoryButton(
    value: String, image: Painter, imageDesc: String?=null
) {
    Surface(
        shape = RoundedCornerShape(50.dp, 50.dp, 20.dp, 20.dp),
        modifier = Modifier.padding(2.dp)
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
                        .background(bg.copy(0.5f))
                        .padding(3.dp),
                    painter = image,
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

//TODO: Favorite Wala Button add karna hai top right side pa
@Composable
fun ItemButton(
    value: String,
    image: Painter,
    imageDesc: String?=null,
    price: String,
    startCount: Double,
    icon :ImageVector
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
                FAB(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(30.dp,0.dp,0.dp,5.dp)
                        .size(33.dp),
                    icon = icon
                )
            }

        }
    }
}


//TODO: FAB ma subtract wala icon chahiye
@Composable
fun CartButton(
    value: String,
    image: Painter,
    imageDesc: String?=null,
    price: String,
    startCount: Double
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
                FAB(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(30.dp,0.dp,0.dp,5.dp)
                        .size(33.dp)
                )
            }

        }
    }
}