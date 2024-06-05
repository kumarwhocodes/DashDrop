package com.dashdrop.data.repo.cart

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

fun addCartInFireBase(itemId: String, operation: Boolean, cnt: Int = 1) {
    val db = Firebase.firestore

    val user = Firebase.auth.currentUser
    val userId = user?.uid ?: return

    db.collection("cart").document(userId).get().addOnSuccessListener { document ->
        if (document.exists()) {
            val data1 = document.get("item_id") as? MutableList<String> ?: mutableListOf()
            val data3 = document.get("item_quantity") as? MutableList<Long>
                ?: mutableListOf()
            val data = mutableMapOf<String, Int>()

            for (i in data1.indices) {
                data[data1[i]] = data3[i].toInt()
            }

            var quantity = data[itemId] ?: 0
            if (operation) {
                quantity+= cnt
            } else {
                if (quantity > 0) {
                    quantity--
                }
            }

            if (quantity > 0) {
                data[itemId] = quantity
            } else {
                data.remove(itemId)
            }

            val updatedIds = data.keys.toList()
            val updatedQuantities = data.values.map { it.toLong() }

            db.collection("cart").document(userId)
                .update("item_id", updatedIds, "item_quantity", updatedQuantities)
        }
    }.addOnFailureListener { exception ->
        Log.w("Can't Get The Data", "Error getting documents.", exception)
    }
}
