package com.dashdrop.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.model.Payment
import com.dashdrop.fireStore.addOrder
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.AddressList
import com.dashdrop.ui.components.CheckoutBottomBar
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.PaymentList
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.rubikBoldStyle

@Composable
fun BillingScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    total: String?
) {
    var selectedAddress by remember { mutableStateOf<DeliveryAddress?>(null) }
    var selectedPayment by remember { mutableStateOf<Payment?>(null) }
    Scaffold(
        topBar = {
            ScaffoldTop(
                toolbarTitle = "Checkout",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    navController.navigate(Screen.CartScreen.route) {
                        popUpTo(Screen.CartScreen.route) { inclusive = true }
                    }
                })
        },
        bottomBar = {
            CheckoutBottomBar(
                buttonText = "Place Order",
                buttonAction = {
                    addOrder(total, selectedAddress, selectedPayment)
                },
                price = total.toString()
            )
        }
    ) {
        BackHandler {
            navController.navigate(Screen.CartScreen.route) {
                popUpTo(Screen.CartScreen.route) { inclusive = true }
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HeadingText(
                        modifier = Modifier,
                        value = "Address",
                        size = 24.sp,
                        color = Color.Black,
                        font = rubikBoldStyle
                    )
                    Text(
                        modifier = Modifier
                            .clickable {
                                       navController.navigate("address/$total")
                            },
                        text = "Add New",
                        fontSize = 24.sp,
                        color = PrimaryColor,
                        fontFamily = rubikBoldStyle)

                }
                AddressList(
                    onAddressSelected = { address ->
                        selectedAddress = address
                    }
                )
                HeadingText(
                    modifier = Modifier,
                    value = "Payment Methods",
                    size = 24.sp,
                    color = Color.Black,
                    font = rubikBoldStyle
                )
                val modes = listOf(
                    Payment(1, "Cash"),
                    Payment(2, "Card"),
                    Payment(3, "UPI")
                )
                PaymentList(modes = modes,
                    onPaymentSelected = { payment ->
                        selectedPayment = payment
                    })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    BillingScreen(navController = rememberNavController(), total = "43")

}