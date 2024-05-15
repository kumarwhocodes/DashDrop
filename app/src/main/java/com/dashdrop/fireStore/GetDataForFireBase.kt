package com.dashdrop.fireStore

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.firestore

//fun addcart(itemId: String, itemCategory: String) {
//    var present: Boolean = false
//    for(i in cartListID){
//        if(i.item_id == itemId){
//            present = true
//            break
//        }
//    }
//    if(!present){
//        cartListID.add(
//            CartItemsId(
//                category_id = itemCategory,
//                item_id = itemId
//            )
//        )
//    }
//}
//
////Temp
//
//var cartListID: MutableList<CartItemsId> = mutableListOf()
//
//val cartItemList = arrayListOf<CartItems>()
////TODO: One Time Data hai abhi sirf isko FireStore ma store karna ka then retrieve karna ka dekhna hai
//fun getcartList(itemId: String){
//    cartItemList.clear()
//    val db = Firebase.firestore
//    db.collection("products")
//        .get()
//        .addOnSuccessListener { documents ->
//            for (document in documents) {
//                if(document.id == itemId){
//                    val data = CartItems(
//                        item_id = itemId,
//                        item_name = document.getString("name") ?: "",
//                        item_price = document.getString("price") ?: "",
//                        item_category = document.getString("category_name") ?: ""
//                    )
//                    cartItemList.add(
//                        data
//                    )
//                }
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.w("Can't Get The Data", "Error getting documents.", exception)
//        }
//}
//
//val favItemList = arrayListOf<FavItems>()
//
//fun getfavList(){
//    favItemList.clear()
//    val db = Firebase.firestore
//    db.collection("products")
//        .get()
//        .addOnSuccessListener { documents ->
//            for (document in documents) {
//                val f = document.getString("favourite") ?: ""
//                if(f == "true"){
//                    val data = FavItems(
//                        item_name = document.getString("name") ?: "",
//                        item_price = document.getString("price") ?: "",
//                        item_star = document.getString("starts") ?: ""
//                    )
//                    favItemList.add(
//                        data
//                    )
//                }
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.w("Can't Get The Data", "Error getting documents.", exception)
//        }
//}
//
//fun changeFav(itemName: String) {
//    val db = Firebase.firestore
//    db.collection("products")
//        .get()
//        .addOnSuccessListener { documents ->
//            for (document in documents) {
//                val name = document.getString("name") ?: ""
//                if(name == itemName){
//                    val fav = document.getString("favourite") ?: ""
//                    if(fav == "true"){
//                        db.collection("products").document(document.id).update("favourite", "false")
//                    }
//                    else
//                        db.collection("products").document(document.id).update("favourite", "true")
//                }
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.w("Can't Get The Data", "Error getting documents.", exception)
//        }
//}

val cartItemList = arrayListOf<CartItems>()

fun addCartinFireBase(itemId: String,operation: Boolean) {
    val db = Firebase.firestore
    val user = com.google.firebase.ktx.Firebase.auth.currentUser
    var data: MutableMap<String, Int> = mutableMapOf()
    var data2: MutableList<String> = mutableListOf()
    db.collection("cart")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                if(document.id == user?.uid){
                    val data1 = document.get("item_id") as MutableList<String>
                    for(i in data1){
                        data[i] = 1
                    }
                    if(operation)
                        data[itemId] = 1
                    else
                        data.remove(itemId)
                    for(i in data){
                        data2.add(i.key)
                    }
                    for(i in data2){
                        Log.d("Datagfdsdfg", i)
                    }
                    db.collection("cart").document(user?.uid ?: "").update("item_id", data2)
                }
            }
            getcartList()
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

fun getcartList(){
    cartItemList.clear()
    val user = com.google.firebase.ktx.Firebase.auth.currentUser
    val db = Firebase.firestore
    val itemids: MutableList<String> = mutableListOf()
    db.collection("cart")
        .document(user?.uid ?: "")
        .get()
        .addOnSuccessListener { document ->
            val data = document.get("item_id") as MutableList<String>
            for (i in data) {
                itemids.add(i)
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
    db.collection("products")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                for (i in itemids) {
                    if (document.id == i) {
                        val data = CartItems(
                            item_name = document.getString("name") ?: "",
                            item_price = document.getString("price") ?: "",
                            item_category = document.getString("category") ?: "",
                            item_id = document.id,
                        )
                        cartItemList.add(
                            data
                        )
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

val favItemList = arrayListOf<FavItems>()

fun changeFav(itemId: String) {
    val db = Firebase.firestore
    val user = com.google.firebase.ktx.Firebase.auth.currentUser
    var data: MutableMap<String, Int> = mutableMapOf()
    var data2: MutableList<String> = mutableListOf()
    db.collection("favourite")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                if(document.id == user?.uid){
                    val data1 = document.get("item_id") as MutableList<String>
                    for(i in data1){
                        data[i] = 1
                    }
                    if(data[itemId] == 1)
                        data.remove(itemId)
                    else
                        data[itemId] = 1
                    for(i in data){
                        data2.add(i.key)
                    }
                    for(i in data2){
                        Log.d("Datagfdsdfg", i)
                    }
                    db.collection("favourite").document(user?.uid ?: "").update("item_id", data2)
                }
            }
            getFavList()
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

fun getFavList(){
    favItemList.clear()
    val user = com.google.firebase.ktx.Firebase.auth.currentUser
    val db = Firebase.firestore
    val itemids: MutableList<String> = mutableListOf()
    db.collection("favourite")
        .document(user?.uid ?: "")
        .get()
        .addOnSuccessListener { document ->
            val data = document.get("item_id") as MutableList<String>
            for (i in data) {
                itemids.add(i)
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
    db.collection("products")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                for (i in itemids) {
                    if (document.id == i) {
                        val data = FavItems(
                            item_name = document.getString("name") ?: "",
                            item_price = document.getString("price") ?: "",
                            item_star = document.getString("star") ?: ""
                        )
                        favItemList.add(
                            data
                        )
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}