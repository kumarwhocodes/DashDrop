package com.dashdrop.presentation.components.cart

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dashdrop.R
import com.dashdrop.data.model.Cart
import com.dashdrop.presentation.components.core.PrimaryButton
import com.dashdrop.presentation.viewmodels.CartViewModel
import com.dashdrop.ui.theme.SecondaryColor
import com.dashdrop.ui.theme.bg
import com.dashdrop.ui.theme.cardBackgroundColor
import com.dashdrop.ui.theme.cardIconBackgroundColor
import com.dashdrop.ui.theme.rubikBoldStyle
import com.dashdrop.ui.theme.rubikRegularStyle
import com.dashdrop.ui.theme.rubikSemiBoldStyle
import com.dashdrop.ui.theme.subtractBackgroundColor

@Composable
fun CartItem(item: Cart,
             cartViewModel: CartViewModel = viewModel(),
             navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(12.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(SecondaryColor)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .background(cardBackgroundColor)
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
                            .background(cardIconBackgroundColor)
                            .fillMaxHeight()
                            .width(100.dp)
                    ) {
                        AsyncImage(
                            model = item.itemImage,
                            contentDescription = "",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(10.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(vertical = 10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = item.itemName,
                            fontFamily = rubikBoldStyle,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Row {
                            Text(
                                text = "₹${item.itemPrice}",
                                fontFamily = rubikRegularStyle,
                                fontSize = 18.sp,
                                color = bg
                            )

                            Text(
                                text = "/KG",
                                fontSize = 18.sp,
                                fontFamily = rubikRegularStyle,
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
                                cartViewModel.updateCartQuantity(
                                    itemId = item.itemId,
                                    operation = false
                                )

                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "minus",
                            colorFilter = ColorFilter.tint(SecondaryColor),
                            modifier = Modifier.background(color = subtractBackgroundColor)
                        )
                    }
                    Text(
                        text = item.itemQuantity.toString(),
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
                                cartViewModel.updateCartQuantity(
                                    itemId = item.itemId,
                                    operation = true
                                )
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
                        Text(text = "Enter Promo Code", fontFamily = rubikRegularStyle)
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
                    onClick = {  }) {
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
fun PricingCard(subTotal: Double, total: Double) {
    Log.d("PricingCard", "Recomposing with subtotal: $subTotal and total: $total")
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
                            color = Color.Black.copy(0.65f),
                            fontFamily = rubikRegularStyle
                        )
                        Text(
                            text = "₹$subTotal",
                            fontSize = 16.sp,
                            fontFamily = rubikSemiBoldStyle
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Delivery Charges",
                            fontSize = 16.sp,
                            color = Color.Black.copy(0.65f),
                            fontFamily = rubikRegularStyle
                        )
                        Text(
                            text = "₹25.00",
                            fontSize = 16.sp,
                            fontFamily = rubikSemiBoldStyle
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Discount",
                            fontSize = 16.sp,
                            color = Color.Black.copy(0.65f),
                            fontFamily = rubikRegularStyle
                        )
                        Text(
                            text = "0.0",
                            fontSize = 16.sp,
                            fontFamily = rubikSemiBoldStyle
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
                            color = Color.Black.copy(0.65f),
                            fontFamily = rubikRegularStyle
                        )
                        Text(
                            text = total.toString(),
                            fontSize = 16.sp,
                            fontFamily = rubikSemiBoldStyle
                        )
                    }

                }
            }
        }
    }

}