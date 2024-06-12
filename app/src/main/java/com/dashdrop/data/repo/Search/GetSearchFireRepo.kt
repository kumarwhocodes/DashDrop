package com.dashdrop.data.repo.Search

import android.util.Log
import com.dashdrop.data.model.SearchItem
import com.dashdrop.data.utils.UiState
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetSearchFireRepo @Inject constructor(
    private val query8: Query
) {

    private val searchItemList = arrayListOf<SearchItem>()

    suspend fun getSearchItemList(): UiState<ArrayList<SearchItem>> {
        searchItemList.clear()

        query8.get().addOnSuccessListener { documents ->
                var a: Int = 0
                for (document in documents) {
                    val data = SearchItem(
                        index = a,
                        itemId = document.id,
                        itemName = document.getString("name") ?: "",
                    )
                    searchItemList.add(
                        data
                    )
                    Log.d("data itemList size", searchItemList.size.toString())
                    a++
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Can't Get The Data", "Error getting documents.", exception)
            }
            .await()

        return if (searchItemList.isNotEmpty()) {
            UiState.Success(searchItemList)
        } else {
            UiState.Error("No Data Found")
        }


    }
}