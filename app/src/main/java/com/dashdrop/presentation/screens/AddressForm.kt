package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.repo.address.addAddress
import com.dashdrop.presentation.viewmodels.BillingUIEvent
import com.dashdrop.presentation.viewmodels.BillingViewModel
import com.dashdrop.presentation.components.core.AddressInputField
import com.dashdrop.presentation.components.core.PrimaryButton
import com.dashdrop.presentation.components.core.SecondaryButton
import com.dashdrop.ui.theme.backgroundColor

@Composable
fun AddressForm(
    navController: NavController,
    billingViewModel: BillingViewModel = hiltViewModel(),
    total: String?
) {
    val uiState by billingViewModel.billingUIState.collectAsState()

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        AddressInputField(
            label = "Name",
            value = uiState.name,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.NameChanged(it))
            }
        )

        AddressInputField(
            label = "Phone Number",
            value = uiState.phoneNumber,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.PhoneNumberChanged(it))
            }
        )

        AddressInputField(
            label = "Pincode",
            value = uiState.pinCode,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.PincodeChanged(it))
            }
        )

        AddressInputField(
            label = "State",
            value = uiState.state,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.StateChanged(it))
            }
        )

        AddressInputField(
            label = "City",
            value = uiState.city,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.CityChanged(it))
            }
        )

        AddressInputField(
            label = "Locality",
            value = uiState.locality,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.LocalityChanged(it))
            }
        )

        AddressInputField(
            label = "Address",
            value = uiState.address,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.AddressChanged(it))
            }
        )

        AddressInputField(
            label = "Landmark",
            value = uiState.landmark,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.LandmarkChanged(it))
            }
        )

        AddressInputField(
            label = "Country",
            value = uiState.country,
            onValueChange = {
                billingViewModel.onEvent(BillingUIEvent.CountryChanged(it))
            },
            action = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            PrimaryButton(onClick = {
                val newAddress = DeliveryAddress(
                    addressId = uiState.addressId,
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
                addAddress(newAddress, navController, total!!)
            }) {
                Text(
                    text = "SAVE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            SecondaryButton(onClick = {
                navController.navigate("billing/$total"){
                    popUpTo("billing/$total")
                }
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