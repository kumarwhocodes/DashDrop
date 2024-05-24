package com.dashdrop.fireStore

import android.util.Log
import androidx.navigation.NavController
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.model.Payment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun addCartinFireBase(itemId: String, operation: Boolean) {
    val db = Firebase.firestore
    val user = Firebase.auth.currentUser
    val userId = user?.uid ?: return

    db.collection("cart").document(userId).get().addOnSuccessListener { document ->
        if (document.exists()) {
            val data1 = document.get("item_id") as? MutableList<String> ?: mutableListOf()
            val data3 = document.get("item_quantity") as? MutableList<Long> ?: mutableListOf() // Change here
            val data = mutableMapOf<String, Int>()

            for (i in data1.indices) {
                data[data1[i]] = data3[i].toInt() // Convert Long to Int
            }

            var quantity = data[itemId] ?: 0
            if (operation) {
                quantity++
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
            val updatedQuantities = data.values.map { it.toLong() } // Convert Int to Long

            db.collection("cart").document(userId)
                .update("item_id", updatedIds, "item_quantity", updatedQuantities)
        }
    }.addOnFailureListener { exception ->
        Log.w("Can't Get The Data", "Error getting documents.", exception)
    }
}


//fun addCartinFireBase(itemId: String, operation: Boolean) {
//    val db = Firebase.firestore
//    val user = com.google.firebase.ktx.Firebase.auth.currentUser
//    var data: MutableMap<String, Int> = mutableMapOf()
//    var data2: MutableList<String> = mutableListOf()
//    var data4: MutableList<Int> = mutableListOf()
//    db.collection("cart").get().addOnSuccessListener { documents ->
//        for (document in documents) {
//            if (document.id == user?.uid) {
//                val data1 = document.get("item_id") as MutableList<String>
//                var data3 = document.get("item_quantity") as MutableList<Int>
//                var a = 0
//                for (i in data1) {
//                    data[i] = data3[a]
//                    a++
//                }
//                var flag: Boolean = true
//                for (i in data1) {
//                    if (i == itemId) {
//                        flag = false
//                        break
//                    }
//                }
//                if (flag) {
//                    data[itemId] = 0
//                }
//                if (operation) {
//                    data[itemId] = data[itemId]!! + 1
//                } else {
//                    data[itemId] = data[itemId]!! - 1
//                    if (data[itemId] == 0) {
//                        data.remove(itemId)
//                    }
//                }
//                for (i in data) {
//                    data2.add(i.key)
//                    data4.add(i.value)
//                    Log.d("Datagfdsdfg", i.key + " " + i.value)
//                }
//                db.collection("cart").document(user?.uid ?: "")
//                    .update("item_id", data2, "item_quantity", data4)
//            }
//        }
//
//    }.addOnFailureListener { exception ->
//        Log.w("Can't Get The Data", "Error getting documents.", exception)
//    }
//}

fun changeFav(itemId: String) {
    val db = Firebase.firestore
    val user = com.google.firebase.ktx.Firebase.auth.currentUser
    var data: MutableMap<String, Int> = mutableMapOf()
    var data2: MutableList<String> = mutableListOf()
    db.collection("favourite").get().addOnSuccessListener { documents ->
        for (document in documents) {
            if (document.id == user?.uid) {
                val data1 = document.get("item_id") as MutableList<String>
                for (i in data1) {
                    data[i] = 1
                }
                if (data[itemId] == 1) data.remove(itemId)
                else data[itemId] = 1
                for (i in data) {
                    data2.add(i.key)
                }
                for (i in data2) {
                    Log.d("Data", i)
                }
                db.collection("favourite").document(user.uid ?: "").update("item_id", data2)
            }
        }
    }.addOnFailureListener { exception ->
        Log.w("Can't Get The Data", "Error getting documents.", exception)
    }
}

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

fun addOrder(total: String?, selectedAddress: DeliveryAddress?, selectedPayment: Payment?) {

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser

    var products: MutableList<String>
    var quantities: MutableList<Int>

    user?.uid?.let { uid ->
        val addressRef = db.collection("cart").document(uid)

        addressRef.get().addOnSuccessListener { document ->
            products = document.get("item_id") as MutableList<String>
            quantities = document.get("item_quantity") as MutableList<Int>
            Log.d("mera_tag", products.toString())
            Log.d("mera_tag", quantities.toString())

            val data = hashMapOf(
                "orderAddressId" to "${selectedAddress?.addressId}",
                "orderCustomerName" to "${selectedAddress?.name}",
                "orderDeliveryAddress" to "${selectedAddress?.address}",
                "orderMethodOfPayment" to "${selectedPayment?.paymentMode}",
                "orderOrderId" to orderUniqueId(),
                "orderPhoneNo" to "${selectedAddress?.phoneNumber}",
                "orderStatus" to "Order Placed",
                "orderTotal" to "$total",
                "userId" to Firebase.auth.currentUser?.uid,
                "orderItems" to products,
                "orderQuantity" to quantities
            )

            db.collection("orders").document(orderUniqueId()).set(data).addOnSuccessListener {
                Log.d("mera_tag", "order placed")
            }.addOnFailureListener { e ->
                Log.w("mera_tag", "Error placing order", e)
            }
        }.addOnFailureListener { exception ->
            Log.d("mera_tag", "Error getting documents.", exception)
        }
    }
}

fun orderUniqueId(): String {
    val dateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    val currentDate = dateFormat.format((Calendar.getInstance().time))
    val timeFormat = SimpleDateFormat("HHmmss", Locale.getDefault())
    val currentTime = timeFormat.format((Calendar.getInstance().time))
    val uid = Firebase.auth.currentUser?.uid.toString()
    return "$currentDate$currentTime&$uid"
}

