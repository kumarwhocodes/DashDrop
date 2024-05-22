package com.dashdrop.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.model.Payment
import com.dashdrop.fireStore.addOrder
import com.dashdrop.navigation.Screen
import com.dashdrop.ui.components.AddressItem
import com.dashdrop.ui.components.AddressList
import com.dashdrop.ui.components.CheckoutBottomBar
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.PaymentList
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.components.SecondaryButton
import com.dashdrop.ui.theme.newBg

@Composable
fun BillingScreen(navController: NavController) {
    val addresses = listOf(
        DeliveryAddress(
            addressId = 1,
            name = "Kumar Sambhav",
            phoneNumber = "6207549371",
            city = "Madhubani",
            state = "Bihar",
            pincode = "847234",
            locality = "Pandaul",
            address = "Pandaul",
            country = "India"
        ),
        DeliveryAddress(
            addressId = 2,
            name = "Kumar Sambhav",
            phoneNumber = "6207549371",
            city = "Madhubani",
            state = "Bihar",
            pincode = "847234",
            locality = "Pandaul",
            address = "Pandaul",
            country = "India"
        ),
        DeliveryAddress(
            addressId = 3,
            name = "Kumar Sambhav",
            phoneNumber = "6207549371",
            city = "Madhubani",
            state = "Bihar",
            pincode = "847234",
            locality = "Pandaul",
            address = "Pandaul",
            country = "India"
        )
    )
    Scaffold(
        topBar = {
            ScaffoldTop(
                toolbarTitle = "Checkout",
                logOutButtonClicked = { /*TODO*/ },
                navigationIconClicked = { /*TODO*/ })
        },
        bottomBar = {
            CheckoutBottomBar(
                buttonText = "Place Order",
                buttonAction = {
                               addOrder()
                },
                price = ""
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HeadingText(
                        modifier = Modifier,
                        value = "Address",
                        size = 20.sp,
                        weight = FontWeight.Bold,
                        color = Color.Black
                    )
                    SecondaryButton(onClick = {
                        navController.navigate(Screen.AddressFormScreen.route)
                    }) {
                        HeadingText(
                            modifier = Modifier,
                            value = "Add New",
                            size = 20.sp,
                            weight = FontWeight.Bold,
                            color = newBg
                        )
                    }

                }
                AddressList(addresses = addresses)
                HeadingText(
                    modifier = Modifier,
                    value = "Payment Methods",
                    size = 20.sp,
                    weight = FontWeight.Bold,
                    color = Color.Black
                )
                val modes = listOf(
                    Payment(1, "Cash"),
                    Payment(2, "Card"),
                    Payment(3, "UPI")
                )
                PaymentList(modes = modes)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    BillingScreen(navController = rememberNavController())

}