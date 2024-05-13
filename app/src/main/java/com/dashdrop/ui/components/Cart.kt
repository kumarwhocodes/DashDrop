package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.fireStore.CartItems
import com.dashdrop.R
import com.dashdrop.ui.theme.bg

@Composable
fun CartItem(
    image: Painter,
    item_Id: CartItems,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = bg.copy(0.25f))
                        .fillMaxHeight()
                        .width(100.dp)
                ) {
                    Image(
                        painter = image,
                        contentDescription = "orange",
                        modifier = Modifier.size(100.dp)
                    )

                }

                Spacer(modifier = Modifier.width(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = item_Id.item_name,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )

                    Text(
                        text = item_Id.item_category,
                        fontSize = 16.sp
                    )

                    Row {
                        Text(
                            text = "₹${item_Id.item_price}",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            color = bg
                        )

                        Text(
                            text = "/KG",
                            fontSize = 16.sp
                        )
                    }

                }

                Spacer(modifier = Modifier.width(50.dp))

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(100.dp))
                            .background(color = Color.Black.copy(0.15f))
                            .size(25.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "add",
                            colorFilter = ColorFilter.tint(Color.Black.copy(0.5f))
                        )
                    }
                    Text(
                        text = "1 KG",
                        fontSize = 16.sp,
                        color = Color.Black.copy(0.5f),
                        fontWeight = FontWeight.Normal
                    )
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(100.dp))
                            .background(color = Color.Black.copy(0.15f))
                            .size(25.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "add",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.background(color = bg)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PromoCode(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(shape = RoundedCornerShape(50.dp))
                .background(color = Color.Black.copy(0.10f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(0.75f)
                        .padding(bottom = 8.dp),
                    value = "", onValueChange = {},
                    label = {
                        Text(text = "Enter Promo Code")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedLabelColor = Color.Black.copy(0.5f),
                        focusedLabelColor = Color.Transparent
                    )
                )

                PrimaryButton(modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .fillMaxHeight(),
                    shapes = RoundedCornerShape(0.dp),
                    onClick = { /*TODO*/ }) {
                    Text(
                        text = "Apply",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PricingCard() {
    Surface {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = RoundedCornerShape(50.dp))
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PromoCode()
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Sub Total",
                            fontSize = 16.sp,
                            color = Color.Black.copy(0.65f)
                        )
                        Text(
                            text = "₹100.00",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Delivery Charges",
                            fontSize = 16.sp,
                            color = Color.Black.copy(0.65f)
                        )
                        Text(
                            text = "₹25.00",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Discount",
                            fontSize = 16.sp,
                            color = Color.Black.copy(0.65f)
                        )
                        Text(
                            text = "₹00.00",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    HorizontalDivider()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Final Total",
                            fontSize = 16.sp,
                            color = Color.Black.copy(0.65f)
                        )
                        Text(
                            text = "₹125.00",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    Column {
        CartItem(
            image = painterResource(id = R.drawable.orange),
            item_Id = CartItems(
                item_id = "Orange",
                item_category = "Fruits",
                item_name = "cff",
                item_price = "fd"
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        PromoCode()
        Spacer(modifier = Modifier.height(50.dp))
        PricingCard()
    }
}