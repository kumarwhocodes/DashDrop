package com.dashdrop.presentation.viewmodels

sealed class BillingUIEvent {

    data class NameChanged(val name: String) : BillingUIEvent()
    object AddressIdChanges : BillingUIEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : BillingUIEvent()
    data class PincodeChanged(val pincode: String) : BillingUIEvent()
    data class LocalityChanged(val locality: String) : BillingUIEvent()
    data class AddressChanged(val address: String) : BillingUIEvent()
    data class CityChanged(val city: String) : BillingUIEvent()
    data class StateChanged(val state: String) : BillingUIEvent()
    data class LandmarkChanged(val landmark: String) : BillingUIEvent()
    data class CountryChanged(val country: String) : BillingUIEvent()

}