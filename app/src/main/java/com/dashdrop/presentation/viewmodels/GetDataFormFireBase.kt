package com.dashdrop.presentation.viewmodels

import android.util.Log
import com.dashdrop.domain.models.CategoryData
import com.dashdrop.domain.models.ItemData
import com.dashdrop.domain.models.ItemDetails
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

val categoryList = arrayListOf<CategoryData>()

val itemList = arrayListOf<ItemData>()

val itemDetailsList = arrayListOf<ItemDetails>()
fun getCategoryList(){
    categoryList.clear()
    val db = Firebase.firestore
    db.collection("Category")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val data = CategoryData(
                    category_name = document.getString("category_name") ?: "",
                    categoryId = document.id
                )
                categoryList.add(
                    data
                )
                Log.d("Data", categoryList.size.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

fun getItemList(path: String){
    itemList.clear()
    val db = Firebase.firestore
    db.collection(path)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val data = ItemData(
                    item_id = document.id,
                    item_name = document.getString("item_name") ?: "",
                    item_price = document.getString("item_price") ?: "",
                    item_favourite = document.getString("item_favourite") ?: "",
                    item_star = document.getString("item_price") ?: ""
                )
                itemList.add(
                    data
                )
                Log.d("Data", itemList.size.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

fun getItemDetails(path: String){
    itemDetailsList.clear()
    val db = Firebase.firestore
    db.collection(path)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val data = ItemDetails(
                    item_id = document.id,
                    item_name = document.getString("item_name") ?: "",
                    item_price = document.getString("item_price") ?: "",
                    item_favourite = document.getString("item_favourite") ?: "",
                    item_star = document.getString("item_price") ?: "",
                    item_detail = document.getString("item_detail")?: ""
                )
                itemDetailsList.add(
                    data
                )
                Log.d("Data", itemDetailsList.size.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}