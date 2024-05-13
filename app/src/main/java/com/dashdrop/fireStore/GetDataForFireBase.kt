package com.dashdrop.fireStore

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

fun addcart(itemId: String, itemCategory: String) {
    var present: Boolean = false
    for(i in cartListID){
        if(i.item_id == itemId){
            present = true
            break
        }
    }
    if(!present){
        cartListID.add(
            CartItemsId(
                category_id = itemCategory,
                item_id = itemId
            )
        )
    }
}

//Temp

var cartListID: MutableList<CartItemsId> = mutableListOf()

val cartItemList = arrayListOf<CartItems>()
//TODO: One Time Data hai abhi sirf isko FireStore ma store karna ka then retrieve karna ka dekhna hai
fun getcartList(itemId: String, categoryId: String){
    cartItemList.clear()
    val db = Firebase.firestore
    db.collection(categoryId)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                if(document.id == itemId){
                    val data = CartItems(
                        item_id = itemId,
                        item_name = document.getString("item_name") ?: "",
                        item_price = document.getString("price") ?: "",
                        item_category = document.getString("category") ?: ""
                    )
                    cartItemList.add(
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

fun editFavourite(itemId: String, categoryId: String){
    itemDetailsList.clear()
    val db = Firebase.firestore
    db.collection(categoryId)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                if(document.id == itemId && document.getString("favourite") == "false"){
                    document.reference.update("favourite", "true")
                }
                else{
                    document.reference.update("favourite", "false")
                }
                Log.d("Data", itemDetailsList.size.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}