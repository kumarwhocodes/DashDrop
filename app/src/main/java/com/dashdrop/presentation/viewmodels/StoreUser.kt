package com.dashdrop.presentation.viewmodels

import android.util.Log
import com.dashdrop.data.model.toLocalUser
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

fun checkAndStoreUser(
    user: FirebaseUser?
){
    val userRef =
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(user?.uid.toString())

    userRef.get().addOnCompleteListener{task ->
        if(task.isSuccessful){
            val document = task.result
            if(!document.exists()){
                storeUserData(user)
            }
            else{
                Log.d("mera_tag","pehle se stored hai firestore m ye user")
            }
        }
    }

}

private fun storeUserData(user: FirebaseUser?) {
    val userRef =
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(user?.uid.toString())

    userRef.set(user!!.toLocalUser())
        .addOnSuccessListener {
            Log.d("mera_tag","firestore me user store hogya")
        }
        .addOnFailureListener{
            Log.d("mera_tag","firestore me user store NAHI hua")

        }

}
