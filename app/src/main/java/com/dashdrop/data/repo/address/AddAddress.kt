package com.dashdrop.data.repo.address

import android.util.Log
import androidx.navigation.NavController
import com.dashdrop.data.model.DeliveryAddress
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

fun addAddress(newAddress: DeliveryAddress, navController: NavController, total: String) {
    val db = Firebase.firestore
    val user = Firebase.auth.currentUser

    db.collection("address").get().addOnSuccessListener { documents ->
        for (document in documents) {
            if (document.id == user?.uid) {
                db.collection("address").document(user.uid)
                    .update("addresses", FieldValue.arrayUnion(newAddress))
            }
        }
        navController.navigate("billing/$total")
        Log.d("mera_tag", "user ka address save hogya")
    }.addOnFailureListener {
        Log.d("mera_tag", "user ka address save nhi hua")
    }
}