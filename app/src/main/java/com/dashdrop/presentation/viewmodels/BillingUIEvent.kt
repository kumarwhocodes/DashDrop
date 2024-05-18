package com.dashdrop.presentation.viewmodels

sealed class BillingUIEvent {

    data class NameChanged(val name: String) : BillingUIEvent()
    data class AddressIdChanges(val addressId: String): BillingUIEvent()
    data class PhoneNumberChanged(val phoneNumber: Long) : BillingUIEvent()
    data class PincodeChanged(val pincode: Int) : BillingUIEvent()
    data class LocalityChanged(val locality: String) : BillingUIEvent()
    data class AddressChanged(val address: String) : BillingUIEvent()
    data class CityChanged(val city: String) : BillingUIEvent()
    data class StateChanged(val state: String) : BillingUIEvent()
    data class LandmarkChanged(val landmark: String) : BillingUIEvent()
    data class CountryChanged(val country: String) : BillingUIEvent()

}