package com.dashdrop.data.model

data class Item(
    val index: Int,
    val itemCategory: String,
    val itemName: String,
    val itemId: String,
    val itemPrice: String,
    val itemStar: String,
    var itemFavourite: String,
    val itemDetail: String,
    val itemImage: String,
)

data class PopularItem(
    val index: Int,
    val itemCategory: String,
    val itemName: String,
    val itemId: String,
    val itemPrice: String,
    val item_image: String
)

data class SearchItem(
    val index: Int,
    val itemName: String,
    val itemId: String,
)