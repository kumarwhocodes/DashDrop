package com.dashdrop.data.model

data class Cart(
    val itemCategory: String,
    val itemName: String,
    val itemId: String,
    val itemPrice: String,
    var itemQuantity: Int,
    val itemImage: String
)