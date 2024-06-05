package com.dashdrop.presentation.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.model.Payment
import com.dashdrop.data.repo.order.addOrder
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.components.billing.AddressList
import com.dashdrop.presentation.components.core.CheckoutBottomBar
import com.dashdrop.presentation.components.core.HeadingText
import com.dashdrop.presentation.components.billing.OrderPlacedAnimation
import com.dashdrop.presentation.components.billing.PaymentList
import com.dashdrop.presentation.components.core.ScaffoldTop
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.rubikBoldStyle
import kotlinx.coroutines.delay

@Composable
fun BillingScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    total: String?
) {
    val context = LocalContext.current
    var selectedAddress by remember { mutableStateOf<DeliveryAddress?>(null) }
    var selectedPayment by remember { mutableStateOf<Payment?>(null) }

    var orderPlaced by remember { mutableStateOf(false) }

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
                    if (selectedAddress != null && selectedPayment != null) {
                        addOrder(total, selectedAddress, selectedPayment) {
                            orderPlaced = true
                        }
                    } else {
                        if (selectedAddress == null && selectedPayment == null) {
                            Toast.makeText(
                                context,
                                "Please select address and payment mode.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (selectedAddress == null) {
                            Toast.makeText(
                                context,
                                "Please select address.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else{
                            Toast.makeText(
                                context,
                                "Please select payment mode.",
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                price = total.toString()
            )
        }
    ) {
        BackHandler {

        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HeadingText(
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
                        fontFamily = rubikBoldStyle
                    )

                }
                AddressList(
                    onAddressSelected = { address ->
                        selectedAddress = address
                    }
                )
                HeadingText(
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
            OrderPlacedDialog(
                showDialog = orderPlaced,
                onDismiss = {
                    orderPlaced = false
                    navController.navigate(Screen.HomeScreen.route)
                }
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderPlacedDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        LaunchedEffect(Unit) {
            delay(2000)
            onDismiss()
        }
        BasicAlertDialog(
            onDismissRequest = onDismiss,
            content = {
                OrderPlacedAnimation()
            }
        )
    }
}