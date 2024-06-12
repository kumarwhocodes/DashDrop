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