package com.dashdrop.data.repo.order

import android.util.Log
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.model.Payment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun addOrder(
    total: String?,
    selectedAddress: DeliveryAddress?,
    selectedPayment: Payment?,
    onOrderPlaced: () -> Unit
) {

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
                onOrderPlaced()
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

