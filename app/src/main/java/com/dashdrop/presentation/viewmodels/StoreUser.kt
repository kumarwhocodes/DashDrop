package com.dashdrop.presentation.viewmodels

import android.util.Log
import com.dashdrop.data.model.toLocalUser
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

fun checkAndStoreUser(
    user: FirebaseUser?
) {
    val userRef =
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(user?.uid.toString())

    userRef.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val document = task.result
            if (!document.exists()) {
                storeUserData(user)
            } else {
                Log.d("mera_tag", "pehle se stored hai firestore m ye user")
            }
        }
    }

}

private fun storeUserData(user: FirebaseUser?) {
    val userRef =
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(user?.uid.toString())
    userRef.set(user?.toLocalUser()!!)
        .addOnSuccessListener {
            Log.d("mera_tag", "firestore me user store hogya")
        }
        .addOnFailureListener {
            Log.d("mera_tag", "firestore me user store NAHI hua")

        }

    val cartData = hashMapOf(
        "item_id" to arrayListOf<String>(),
        "item_quantity" to arrayListOf<String>()
    )
    val favouriteData = hashMapOf(
        "item_id" to arrayListOf<String>()
    )

    FirebaseFirestore.getInstance()
        .collection("cart")
        .document(user.uid)
        .set(cartData)
        .addOnSuccessListener {
            Log.d("mera_tag", "firestore me cart document store hogya")
        }
        .addOnFailureListener {
            Log.d("mera_tag", "firestore me cart document store NAHI hua")
        }

    FirebaseFirestore.getInstance()
        .collection("favourite")
        .document(user?.uid.toString())
        .set(favouriteData)
        .addOnSuccessListener {
            Log.d("mera_tag", "firestore me cart document store hogya")
        }
        .addOnFailureListener {
            Log.d("mera_tag", "firestore me cart document store NAHI hua")
        }

    val addressData = hashMapOf("addresses" to arrayListOf<String>())

    FirebaseFirestore.getInstance()
        .collection("address")
        .document(user.uid)
        .set(addressData)
        .addOnSuccessListener {
            Log.d("mera_tag", "firestore me address document store hogya")
        }
        .addOnFailureListener {
            Log.d("mera_tag", "firestore me address document store NAHI hua")
        }

    val orderData = hashMapOf("orders" to arrayListOf<String>())

    FirebaseFirestore.getInstance()
        .collection("orders")
        .document(user.uid)
        .set(orderData)
        .addOnSuccessListener {
            Log.d("mera_tag", "firestore me order document store hogya")
        }
        .addOnFailureListener {
            Log.d("mera_tag", "firestore me order document store NAHI hua")
        }
}
