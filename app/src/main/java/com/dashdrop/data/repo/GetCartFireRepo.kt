package com.dashdrop.data.repo

import android.util.Log
import com.dashdrop.data.model.Cart
import com.dashdrop.data.utils.UiState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetCartFireRepo @Inject constructor(
    private val query3: DocumentReference,
    private val query4: Query,
) {
    val cartList = arrayListOf<Cart>()

    suspend fun getCartList(): UiState<ArrayList<Cart>> {
        val itemIds: MutableList<String> = mutableListOf()
        cartList.clear()

        return try {

            val userCartSnapshot = query3.get().await()
            val data = userCartSnapshot.get("item_id") as List<String>
            val quantity = userCartSnapshot.get("item_quantity") as List<Int>
            itemIds.addAll(data)
            Log.d("GetCartFireRepo", "itemIds: $itemIds")

            val itemsSnapshot = query4.get().await()
            for (document in itemsSnapshot) {
                if (document.id in itemIds) {
                    val data = Cart(
                        item_name = document.getString("name") ?: "",
                        item_price = document.getString("price") ?: "",
                        item_category = document.getString("category") ?: "",
                        item_id = document.id,
                        item_quantity = quantity[itemIds.indexOf(document.id)]
                    )
                    cartList.add(data)
                }
            }

            if (cartList.isNotEmpty()) {
                UiState.Success(cartList)
            } else {
                UiState.Error("No Data Found")
            }
        } catch (e: Exception) {
            Log.e("GetCartFireRepo", "Error getting documents: ${e.message}")
            UiState.Error("Error fetching cart items")
        }
    }
}