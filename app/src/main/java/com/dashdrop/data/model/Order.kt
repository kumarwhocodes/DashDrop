package com.dashdrop.data.model

data class Order(
    val orderAddressId: String,
    val orderCustomerName: String,
    val orderDeliveryAddress: String,
    val orderMethodOfPayment: String,
    val orderOrderId: String,
    val orderPhoneNo: Long,
    val orderStatus: String,
    val orderTotal: Double,
    val userId: String
)
