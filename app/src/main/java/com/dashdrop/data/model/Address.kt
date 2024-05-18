package com.dashdrop.data.model

data class DeliveryAddress(
    val addressId: Int,
    val name: String,
    val phoneNumber: Long,
    val pincode: Int,
    val locality: String,
    val address: String,
    val city: String,
    val state: String,
    val landmark: String?=null,
    val country: String
)

data class BillingAddress(
    val addressId: Int,
    val name: String,
    val phoneNumber: Int,
    val pincode: Int,
    val locality: String,
    val address: String,
    val city: String,
    val state: String,
    val landmark: String?=null,
    val country: String
)

