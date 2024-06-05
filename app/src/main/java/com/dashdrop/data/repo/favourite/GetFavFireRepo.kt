package com.dashdrop.data.repo.favourite

import android.util.Log
import com.dashdrop.data.model.Favourite
import com.dashdrop.data.utils.UiState
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetFavFireRepo @Inject constructor(
    private val query3: DocumentReference,
    private val query4: Query,
) {
    private val favList = arrayListOf<Favourite>()

    suspend fun getFavList(): UiState<ArrayList<Favourite>> {
        val itemIds: MutableList<String> = mutableListOf()
        favList.clear()

        return try {

            val userFavSnapshot = query3.get().await()
            val item = userFavSnapshot.get("item_id") as List<String>
            Log.d("GetFavFireRepo", "item id: $item")
            itemIds.addAll(item)
            Log.d("GetFavFireRepo", "itemIds: $itemIds")

            val itemsSnapshot = query4.get().await()
            for (document in itemsSnapshot) {
                if (document.id in itemIds) {
                    val data = Favourite(
                        itemName = document.getString("name") ?: "",
                        itemPrice = document.getString("price") ?: "",
                        itemStar = document.getString("star") ?: "",
                        itemImage = document.getString("image") ?: "",
                    )
                    favList.add(data)
                }
            }

            if (favList.isNotEmpty()) {
                UiState.Success(favList)
            } else {
                UiState.Success(favList)
            }
        } catch (e: Exception) {
            Log.e("GetFavFireRepo", "Error getting documents: ${e.message}")
            UiState.Error("Error fetching favourite items")
        }
    }
}