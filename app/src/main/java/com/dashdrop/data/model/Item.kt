package com.dashdrop.data.model

data class Item(
    val index: Int,
    val item_category: String,
    val item_name: String,
    val item_id: String,
    val item_price: String,
    val item_star: String,
    var item_favourite: String,
    val item_detail: String
)

data class PopularItem(
    val index: Int,
    val item_category: String,
    val item_name: String,
    val item_id: String,
    val item_price: String,
)
