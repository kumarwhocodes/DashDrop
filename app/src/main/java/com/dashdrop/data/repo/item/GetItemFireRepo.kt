package com.dashdrop.data.repo.item

import android.util.Log
import androidx.navigation.NavController
import com.dashdrop.data.model.Item
import com.dashdrop.data.model.PopularItem
import com.dashdrop.data.utils.UiState
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetItemFireRepo @Inject constructor(
    private val query2: Query,
    private val query6: Query
) {

    val itemList = arrayListOf<Item>()
    val popularItemList = arrayListOf<PopularItem>()

    suspend fun getItemList(path: String, navController: NavController): UiState<ArrayList<Item>> {
        itemList.clear()

        query2.get().addOnSuccessListener { documents ->
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
//                navController.navigate("category/$path")
            }
            .addOnFailureListener { exception ->
                Log.w("Can't Get The Data", "Error getting documents.", exception)
            }
            .await()

        return if (itemList.isNotEmpty()) {
            UiState.Success(itemList)
        } else {
            UiState.Error("No Data Found")
        }


    }

    suspend fun getPopularItemsList(): UiState<ArrayList<PopularItem>> {
        popularItemList.clear()

        query6.get().addOnSuccessListener { documents ->
            var a: Int = 0
            for (document in documents) {
                val data = PopularItem(
                    index = a,
                    item_category = document.getString("category_name") ?: "",
                    item_id = document.id,
                    item_name = document.getString("name") ?: "",
                    item_price = document.getString("price") ?: ""
                )
                popularItemList.add(
                    data
                )
                a++
            }
        }
            .addOnFailureListener { exception ->
                Log.w("Can't Get The Data", "Error getting documents.", exception)
            }
            .await()

        return if (popularItemList.isNotEmpty()) {
            UiState.Success(popularItemList)
        } else {
            UiState.Error("No Data Found")
        }


    }
}