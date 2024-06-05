package com.dashdrop.presentation.components.home

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dashdrop.R
import com.dashdrop.data.repo.cart.addCartInFireBase
import com.dashdrop.ui.theme.TertiaryColor
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.cardBackgroundColor
import com.dashdrop.ui.theme.cardIconBackgroundColor
import com.dashdrop.ui.theme.rubikSemiBoldStyle

@Composable
fun CategoryButton(
    value: String, image: String, imageDesc: String?=null,
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
                AsyncImage(
                    model = image,
                    contentDescription = imageDesc,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(cardIconBackgroundColor)
                        .padding(10.dp)
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
    image: String,
    imageDesc: String? = null,
    price: String,
    itemId: String
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
                        AsyncImage(
                            model = image,
                            contentDescription = imageDesc,
                            modifier = Modifier
                                .size(183.dp, 129.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(cardIconBackgroundColor)
                                .padding(10.dp)
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
                    addCartInFireBase(itemId = itemId, true)
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