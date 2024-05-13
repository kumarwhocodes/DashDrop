package com.dashdrop.fireStore

data class CategoryData(
    val category_name: String,
    val categoryId: String,
)

data class ItemData(
    val item_category: String,
    val item_name: String,
    val item_id: String,
    val item_price: String,
    val item_star: String,
    var item_favourite: String
)

data class ItemDetails(
    val item_name: String,
    val item_id: String,
    val item_price: String,
    val item_star: String,
    var item_favourite: String,
    val item_detail: String
)

data class CartItemsId(
    val category_id: String,
    val item_id: String,
)

data class CartItems(
    val item_category: String,
    val item_name: String,
    val item_id: String,
    val item_price: String
)