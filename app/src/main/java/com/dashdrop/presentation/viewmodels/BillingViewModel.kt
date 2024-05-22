package com.dashdrop.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BillingViewModel : ViewModel(){

    private var billingUIState = MutableStateFlow(BillingUIState())
    val _billingUIState : StateFlow<BillingUIState> = billingUIState



    fun onEvent(event: BillingUIEvent, navController: NavController){
        when(event){
            is BillingUIEvent.AddressChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    address = event.address
                )
            }
            is BillingUIEvent.AddressIdChanges -> {
                billingUIState.value = billingUIState.value.copy(
                    addressId = event.addressId
                )
            }
            is BillingUIEvent.CityChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    city = event.city
                )
            }
            is BillingUIEvent.CountryChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    country = event.country
                )
            }
            is BillingUIEvent.LandmarkChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    landmark = event.landmark
                )
            }
            is BillingUIEvent.LocalityChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    locality = event.locality
                )
            }
            is BillingUIEvent.NameChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    name = event.name
                )
            }
            is BillingUIEvent.PhoneNumberChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            is BillingUIEvent.PincodeChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    pinCode = event.pincode
                )
            }
            is BillingUIEvent.StateChanged -> {
                billingUIState.value = billingUIState.value.copy(
                    state = event.state
                )
            }
        }
    }

}