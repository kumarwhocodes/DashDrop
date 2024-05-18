package com.dashdrop.fireStore

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.firestore

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
//            getcartList()

        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}

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
        }
        .addOnFailureListener { exception ->
            Log.w("Can't Get The Data", "Error getting documents.", exception)
        }
}