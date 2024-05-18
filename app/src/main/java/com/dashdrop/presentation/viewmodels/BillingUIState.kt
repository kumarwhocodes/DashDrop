package com.dashdrop.presentation.viewmodels

data class BillingUIState (
    var name: String = "",
    var addressId: String = "",
    var phoneNumber: Long = 0,
    var pincode: Int = 0,
    var locality: String = "",
    var address: String = "",
    var city: String = "",
    var state: String = "",
    var landmark: String = "",
    var country: String = "",
)