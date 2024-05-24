package com.dashdrop.data.model

data class DeliveryAddress(
    val addressId: Int?=null,
    val name: String?=null,
    val phoneNumber: String?=null,
    val pincode: String?=null,
    val locality: String?=null,
    val address: String?=null,
    val city: String?=null,
    val state: String?=null,
    val landmark: String?=null,
    val country: String?=null
)

data class BillingAddress(
    val addressId: Int,
    val name: String,
    val phoneNumber: String,
    val pincode: String,
    val locality: String,
    val address: String,
    val city: String,
    val state: String,
    val landmark: String?=null,
    val country: String
)

