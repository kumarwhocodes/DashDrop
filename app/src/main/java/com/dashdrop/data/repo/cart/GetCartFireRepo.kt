package com.dashdrop.data.repo.cart

import android.util.Log
import com.dashdrop.data.model.Cart
import com.dashdrop.data.utils.UiState
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetCartFireRepo @Inject constructor(
    private val query3: DocumentReference,
    private val query4: Query,
) {
    private val cartList = arrayListOf<Cart>()

    fun clearCart(){

        cartList.clear()
        val arrayListOf = arrayListOf<String>()
        query3.update("item_id", arrayListOf,"item_quantity",arrayListOf)

    }
    suspend fun getCartList(): UiState<Pair<ArrayList<Cart>,Double>> {
        val itemIds: MutableList<String> = mutableListOf()
        cartList.clear()

        return try {

            val userCartSnapshot = query3.get().await()
            val item = userCartSnapshot.get("item_id") as List<String>
            val quantity = userCartSnapshot.get("item_quantity") as List<Int>
            itemIds.addAll(item)
            Log.d("GetCartFireRepo", "itemIds: $itemIds")

            var total = 0.0
            val itemsSnapshot = query4.get().await()
            for (document in itemsSnapshot) {
                if (document.id in itemIds) {
                    val data = Cart(
                        itemName = document.getString("name") ?: "",
                        itemPrice = document.getString("price") ?: "",
                        itemCategory = document.getString("category") ?: "",
                        itemId = document.id,
                        itemQuantity = quantity[itemIds.indexOf(document.id)],
                        itemImage = document.getString("image") ?: ""
                    )
                    total += data.itemPrice.toDouble() * data.itemQuantity
                    cartList.add(data)
                }
            }

            if (cartList.isNotEmpty()) {
                UiState.Success(Pair(cartList, total))
            } else {
                UiState.Success(Pair(cartList, 0.0))
            }
        } catch (e: Exception) {
            Log.e("GetCartFireRepo", "Error getting documents: ${e.message}")
            UiState.Error("Error fetching cart items")
        }
    }
}