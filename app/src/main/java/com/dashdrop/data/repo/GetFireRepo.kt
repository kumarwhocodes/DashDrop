package com.dashdrop.data.repo

import android.util.Log
import androidx.navigation.NavController
import com.dashdrop.data.model.Category
import com.dashdrop.data.model.Item
import com.dashdrop.data.utils.UiState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class GetFireRepo {
    val categoryList = arrayListOf<Category>()

    val itemList = arrayListOf<Item>()

    suspend fun getCategoryList(): UiState<ArrayList<Category>> {
        categoryList.clear()
        val db = Firebase.firestore

        db.collection("Category")
            .get()
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


    suspend fun getItemList(path: String, navController: NavController): UiState<ArrayList<Item>> {
        itemList.clear()
        val db = Firebase.firestore
        db.collection("products")
            .get()
            .addOnSuccessListener { documents ->
                var a: Int = 0
                for (document in documents) {
                    val t = document.getString("category_name") ?: ""
                    Log.d("data category name", t)
                    Log.d("data given category name", path)
                    if (t == path) {
                        val data = Item(
                            index = a,
                            item_category = path,
                            item_id = document.id,
                            item_name = document.getString("name") ?: "",
                            item_price = document.getString("price") ?: "",
                            item_favourite = document.getString("favourite") ?: "",
                            item_star = document.getString("stars") ?: "",
                            item_detail = document.getString("details") ?: ""
                        )
                        itemList.add(
                            data
                        )
                        Log.d("data itemList size", itemList.size.toString())
                        a++
                    }
                }
                navController.navigate("category/$path")
            }
            .addOnFailureListener { exception ->
                Log.w("Can't Get The Data", "Error getting documents.", exception)
            }
            .await()

        return if (itemList.isNotEmpty()){
            UiState.Success(itemList)
        }else{
            UiState.Error("No Data Found")
        }


    }
}