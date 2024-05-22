package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.data.model.Cart
import com.dashdrop.fireStore.addCartinFireBase
import com.dashdrop.ui.theme.bg

@Composable
fun CartItem(item: Cart) {
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
                .background(color = bg.copy(0.5f))
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
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
                            painter = painterResource(id = R.drawable.orange),
                            contentDescription = "orange",
                            modifier = Modifier.size(100.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(vertical = 10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = item.item_name,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            color = Color.Black
                        )

                        Text(
                            text = item.item_category,
                            fontSize = 16.sp
                        )

                        Row {
                            Text(
                                text = "₹${item.item_price}",
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
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(100.dp))
                            .background(color = Color.Black.copy(0.15f))
                            .size(25.dp)
                            .clickable {
                                addCartinFireBase(itemId = item.item_id, operation = false)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "minus",
                            colorFilter = ColorFilter.tint(Color.Black.copy(0.5f))
                        )
                    }
                    Text(
                        text = item.item_quantity.toString(),
                        fontSize = 16.sp,
                        color = Color.Black.copy(0.5f),
                        fontWeight = FontWeight.Normal
                    )
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(100.dp))
                            .background(color = Color.Black.copy(0.15f))
                            .size(25.dp)
                            .clickable {
                                addCartinFireBase(item.item_id, true)
                            }
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
    HorizontalDivider(
        color = bg,
        thickness = 2.dp
    )
}

@Composable
fun PromoCode() {
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
fun PricingCard(subTotal: Double) {
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
                            text = "₹$subTotal",
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
                            text = "0.0",
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
                            text = (subTotal+25.00).toString(),
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
            item = Cart(
                item_id = "111",
                item_category = "Fruits",
                item_name = "Orange",
                item_price = "15",
                item_quantity = 5
            )
        )
        CartItem(
            item = Cart(
                item_id = "111",
                item_category = "Fruits",
                item_name = "Orange",
                item_price = "15",
                item_quantity = 5
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        PricingCard(5.0)
    }
}