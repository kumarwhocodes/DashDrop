package com.dashdrop.data.repo.address

import android.util.Log
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.utils.UiState
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetAddressFireRepo @Inject constructor(
    private val query5: DocumentReference
) {

    suspend fun getAddress(): UiState<List<DeliveryAddress>> {
        return try {
            val userAddressSnapshot = query5.get().await()
            val data = userAddressSnapshot["addresses"] as? List<Map<String, Any>>

            val addressesList = data?.mapNotNull { map ->
                try {
                    DeliveryAddress(
                        addressId = (map["addressId"] as? Long)?.toInt() ?: return@mapNotNull null,
                        name = map["name"] as? String ?: "",
                        phoneNumber = map["phoneNumber"] as? String ?: "",
                        city = map["city"] as? String ?: "",
                        state = map["state"] as? String ?: "",
                        `pin-code` = map["`pincode`"] as? String ?: "",
                        locality = map["locality"] as? String ?: "",
                        address = map["address"] as? String ?: "",
                        country = map["country"] as? String ?: ""
                    )
                } catch (e: Exception) {
                    null
                }
            } ?: emptyList()

            if (addressesList.isNotEmpty()) {
                Log.d("GetAddressFireRepo", "addressesList: $addressesList")
                UiState.Success(addressesList)
            } else {
                UiState.Error("No Data Found")
            }
        } catch (e: Exception) {
            Log.e("GetAddressFireRepo", "Error getting address data", e)
            UiState.Error("Error fetching data: ${e.message}")
        }
    }
}