package com.dashdrop.data.repo.item

import android.util.Log
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

    private val itemList = arrayListOf<Item>()
    private val popularItemList = arrayListOf<PopularItem>()

    suspend fun getItemList(path: String): UiState<ArrayList<Item>> {
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
                            itemCategory = path,
                            itemId = document.id,
                            itemName = document.getString("name") ?: "",
                            itemPrice = document.getString("price") ?: "",
                            itemFavourite = document.getString("favourite") ?: "",
                            itemStar = document.getString("stars") ?: "",
                            itemDetail = document.getString("details") ?: "",
                            itemImage = document.getString("image") ?: ""
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
            var a = 0
            for (document in documents) {
                val data = PopularItem(
                    index = a,
                    itemCategory = document.getString("category_name") ?: "",
                    itemId = document.id,
                    itemName = document.getString("name") ?: "",
                    itemPrice = document.getString("price") ?: "",
                    item_image = document.getString("image") ?: ""
                )
                popularItemList.add(
                    data
                )
                a++
                Log.d("data popularItemList size", data.toString())
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