package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.fireStore.addAddress
import com.dashdrop.presentation.viewmodels.BillingUIEvent
import com.dashdrop.presentation.viewmodels.BillingUIState
import com.dashdrop.presentation.viewmodels.BillingViewModel
import com.dashdrop.ui.components.CustomInputField
import com.dashdrop.ui.components.PrimaryButton
import com.dashdrop.ui.components.SecondaryButton
import com.dashdrop.ui.components.TextField_Text

@Composable
fun AddressForm(
    navController: NavController,
    billingViewModel: BillingViewModel = viewModel()
) {
    val uiState by billingViewModel._billingUIState.collectAsState()
    Column {

        TextField_Text(
            modifier = Modifier,
            labelValue = "Name"
        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = uiState.name, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.NameChanged(it),
                navController
            )
        })

        TextField_Text(
            modifier = Modifier,
            labelValue = "Phone Number"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.phoneNumber, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.PhoneNumberChanged(it),
                navController
            )
        })

        TextField_Text(
            modifier = Modifier,
            labelValue = "Pincode"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.pinCode, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.PincodeChanged(it),
                navController
            )
        })

        TextField_Text(
            modifier = Modifier,
            labelValue = "State"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.state, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.StateChanged(it),
                navController
            )
        })

        TextField_Text(
            modifier = Modifier,
            labelValue = "City"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.city, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.CityChanged(it),
                navController
            )
        })

        TextField_Text(
            modifier = Modifier,
            labelValue = "Locality"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.locality, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.LocalityChanged(it),
                navController
            )
        })

        TextField_Text(
            modifier = Modifier,
            labelValue = "Address"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.address, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.AddressChanged(it),
                navController
            )
        })
        TextField_Text(
            modifier = Modifier,
            labelValue = "Landmark"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.landmark, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.LandmarkChanged(it),
                navController
            )
        })

        TextField_Text(
            modifier = Modifier,
            labelValue = "Country"
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = uiState.country, onValueChange = {
            billingViewModel.onEvent(
                BillingUIEvent.CountryChanged(it),
                navController
            )
        })

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            PrimaryButton(onClick = {

                val newAddress = DeliveryAddress(
                    addressId = 1,
                    name = uiState.name,
                    phoneNumber = uiState.phoneNumber,
                    pincode = uiState.pinCode,
                    locality = uiState.locality,
                    address = uiState.address,
                    city = uiState.city,
                    state = uiState.state,
                    landmark = uiState.landmark,
                    country = uiState.country
                )
                addAddress(newAddress, navController)
            }) {
                Text(
                    text = "SAVE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            SecondaryButton(onClick = {

            }) {
                Text(
                    text = "CANCEL",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    AddressForm(
        navController = rememberNavController(),
        billingViewModel = BillingViewModel()
    )
}