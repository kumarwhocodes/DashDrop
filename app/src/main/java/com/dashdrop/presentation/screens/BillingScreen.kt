package com.dashdrop.presentation.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.dashdrop.presentation.viewmodels.CartViewModel
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.backgroundColor
import com.dashdrop.ui.theme.rubikBoldStyle
import com.dashdrop.ui.theme.rubikMediumStyle
import com.dashdrop.ui.theme.rubikRegularStyle
import com.dashdrop.ui.theme.rubikSemiBoldStyle
import kotlinx.coroutines.delay

@Composable
fun BillingScreen(
    signInViewModel: SignInViewModel = viewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
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
                        cartViewModel.clearCart()
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
            navController.navigate(Screen.CartScreen.route) {
                popUpTo(Screen.CartScreen.route) { inclusive = true }
            }
        }
        Surface(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .background(backgroundColor)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HeadingText(
                        value = "Address",
                        size = 22.sp,
                        color = Color.Black,
                        font = rubikRegularStyle
                    )
                    Text(
                        modifier = Modifier
                            .clickable {
                                navController.navigate("address/$total")
                            },
                        text = "Add New",
                        fontSize = 22.sp,
                        color = PrimaryColor,
                        fontFamily = rubikRegularStyle
                    )

                }
                AddressList(
                    onAddressSelected = { address ->
                        selectedAddress = address
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                HeadingText(
                    value = "Payment Methods",
                    size = 24.sp,
                    color = Color.Black,
                    font = rubikRegularStyle
                )
                val modes = listOf(
                    Payment(1, "Cash", "https://firebasestorage.googleapis.com/v0/b/dashdrop-1d768.appspot.com/o/payments%2FCash.png?alt=media&token=c71ba5ad-b26b-4b6a-85ee-7b388b5c24c2"),
                    Payment(2, "Card", "https://firebasestorage.googleapis.com/v0/b/dashdrop-1d768.appspot.com/o/payments%2FCard.png?alt=media&token=f4be8f18-bb93-4dcc-9327-8ef6139cb0c8"),
                    Payment(3, "UPI", "https://firebasestorage.googleapis.com/v0/b/dashdrop-1d768.appspot.com/o/payments%2FUPI.png?alt=media&token=bac85f1e-d622-485f-81c5-2b4a04143184")
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