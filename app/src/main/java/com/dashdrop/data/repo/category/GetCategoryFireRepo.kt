package com.dashdrop.data.repo.category

import android.util.Log
import com.dashdrop.data.model.Category
import com.dashdrop.data.utils.UiState
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetCategoryFireRepo @Inject constructor(
    private val query1: Query
) {
    val categoryList = arrayListOf<Category>()

    suspend fun getCategoryList(): UiState<ArrayList<Category>> {
        categoryList.clear()
            query1.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val data = Category(
                        category_name = document.getString("category_name") ?: "",
                        categoryId = document.id
                    )
                    categoryList.add(
                        data
                    )
                    Log.d("Data", categoryList.size.toString())
                }

            }
            .addOnFailureListener { exception ->
                Log.w("Can't Get The Data", "Error getting documents.", exception)
            }
            .await()

        return if (categoryList.isNotEmpty()) {
            UiState.Success(categoryList)
        } else {
            UiState.Error("No Data Found")
        }
    }



}