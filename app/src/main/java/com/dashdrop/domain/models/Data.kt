package com.dashdrop.domain.models

data class CategoryData(
    val category_name: String,
    val categoryId: String,
)

data class ItemData(
    val item_name: String,
    val item_id: String,
    val item_price: String,
    val item_star: String,
    val item_favourite: String
)

data class ItemDetails(
    val item_name: String,
    val item_id: String,
    val item_price: String,
    val item_star: String,
    val item_favourite: String,
    val item_detail: String
)