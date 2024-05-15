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
fun getcartList(itemId: String){
    cartItemList.clear()
    val db = Firebase.firestore
    db.collection("products")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                if(document.id == itemId){
                    val data = CartItems(
                        item_id = itemId,
                        item_name = document.getString("name") ?: "",
                        item_price = document.getString("price") ?: "",
                        item_category = document.getString("category_name") ?: ""
                    )
                    cartItemList.add(
                        data
                    )
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

val favItemList = arrayListOf<FavItems>()

fun getfavList(){
    favItemList.clear()
    val db = Firebase.firestore
    db.collection("products")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val f = document.getString("favourite") ?: ""
                if(f == "true"){
                    val data = FavItems(
                        item_name = document.getString("name") ?: "",
                        item_price = document.getString("price") ?: "",
                        item_star = document.getString("starts") ?: ""
                    )
                    favItemList.add(
                        data
                    )
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

fun changeFav(itemName: String) {
    val db = Firebase.firestore
    db.collection("products")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val name = document.getString("name") ?: ""
                if(name == itemName){
                    val fav = document.getString("favourite") ?: ""
                    if(fav == "true"){
                        db.collection("products").document(document.id).update("favourite", "false")
                    }
                    else
                        db.collection("products").document(document.id).update("favourite", "true")
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}