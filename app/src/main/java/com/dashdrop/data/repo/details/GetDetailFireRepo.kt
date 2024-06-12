package com.dashdrop.data.repo.details

import android.util.Log
import com.dashdrop.data.model.Details
import com.dashdrop.data.utils.UiState
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetDetailFireRepo @Inject constructor(
    private val query7: Query
) {
    var data: Details? = null

    suspend fun getDetails(path: String): UiState<Details> {
        query7.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    if (document.id == path) {
                        data = Details(
                            itemCategory = document.getString("category_name") ?: "",
                            itemId = document.id,
                            itemName = document.getString("name") ?: "",
                            itemPrice = document.getString("price") ?: "",
                            itemStar = document.getString("stars") ?: "",
                            itemDetail = document.getString("details") ?: "",
                            itemImage = document.getString("image") ?: ""
                        )
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Can't Get The Data", "Error getting documents.", exception)
            }
            .await()

        return if (data!=null) {
            UiState.Success(data!!)
        } else {
            UiState.Error("No Data Found")
        }


    }
}