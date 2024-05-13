package com.dashdrop.fireStore

import android.util.Log
import androidx.navigation.NavController
import com.dashdrop.navigation.Screen
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

fun getItemList(path: String, navController: NavController){
    itemList.clear()
    val db = Firebase.firestore
    db.collection(path)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val data = ItemData(
                    item_category = path,
                    item_id = document.id,
                    item_name = document.getString("item_name") ?: "",
                    item_price = document.getString("price") ?: "",
                    item_favourite = document.getString("favourite") ?: "",
                    item_star = document.getString("star") ?: ""
                )
                itemList.add(
                    data
                )
                Log.d("Data", itemList.size.toString())
            }
            navController.navigate(Screen.CategoryScreen.route)
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

fun getItemDetails(productId: String, itemCategory: String, navController: NavController){
    itemDetailsList.clear()
    val db = Firebase.firestore
    db.collection(itemCategory)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                if(document.id == productId){
                    val data = ItemDetails(
                        item_id = document.id,
                        item_name = document.getString("item_name") ?: "",
                        item_price = document.getString("price") ?: "",
                        item_favourite = document.getString("favourite") ?: "",
                        item_star = document.getString("star") ?: "",
                        item_detail = document.getString("detail") ?: ""
                    )
                    itemDetailsList.add(
                        data
                    )
                }
                Log.d("Data", itemDetailsList.size.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}