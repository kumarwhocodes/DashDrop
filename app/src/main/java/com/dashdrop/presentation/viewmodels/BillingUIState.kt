package com.dashdrop.presentation.viewmodels

data class BillingUIState (
    var name: String = "",
    var addressId: String = "",
    var phoneNumber: String = "",
    var pinCode: String = "",
    var locality: String = "",
    var address: String = "",
    var city: String = "",
    var state: String = "",
    var landmark: String = "",
    var country: String = "",

    var addressError: Boolean = false,
    var pinCodeError: Boolean = false
)