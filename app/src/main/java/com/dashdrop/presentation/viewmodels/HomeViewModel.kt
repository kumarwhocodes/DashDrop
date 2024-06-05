package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dashdrop.data.model.Category
import com.dashdrop.data.model.Item
import com.dashdrop.data.model.PopularItem
import com.dashdrop.data.repo.category.GetCategoryFireRepo
import com.dashdrop.data.repo.item.GetItemFireRepo
import com.dashdrop.data.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryFireRepo: GetCategoryFireRepo,
    private val itemFireRepo: GetItemFireRepo
) : ViewModel() {

    //    private val repository: GetFireRepo = ServiceLocator.provideRepository()
    private val _categoryData: MutableStateFlow<UiState<ArrayList<Category>>> =
        MutableStateFlow(UiState.Idle)
    val categoryData = _categoryData.asStateFlow()

    private val _itemData: MutableStateFlow<UiState<ArrayList<Item>>> =
        MutableStateFlow(UiState.Idle)
    val itemData = _itemData.asStateFlow()

    private val _popularItemData: MutableStateFlow<UiState<ArrayList<PopularItem>>> =
        MutableStateFlow(UiState.Idle)
    val popularItemData = _popularItemData.asStateFlow()

    fun getAllCategory() {
        Log.d("HomeViewModel", "getAllCategory called")
        getAllCategoryFromFireStore()

    }

    fun getAllItems(path: String, navController: NavController) {
        Log.d("HomeViewModel", "getAllItems called with path: $path")
        getAllItemsFromFireStore(path)
    }

    fun getAllPopularItems() {
        getAllPopularItemsFromFireStore()
    }

    private fun getAllCategoryFromFireStore() {
        _categoryData.value = UiState.Loading
        Log.d("HomeViewModel", "Fetching categories from Firestore")

        viewModelScope.launch {
            try {
                _categoryData.value = categoryFireRepo.getCategoryList()
                Log.d("HomeViewModel", "Categories fetched: $_categoryData")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching categories: ${e.message}")
            }
        }
    }

    private fun getAllItemsFromFireStore(path: String) {
        _itemData.value = UiState.Loading
        Log.d("HomeViewModel", "Fetching items from Firestore for path: $path")

        viewModelScope.launch {
            try {
                _itemData.value = itemFireRepo.getItemList(path)
                Log.d("HomeViewModel", "Items fetched: $_itemData")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching items: ${e.message}")
            }
        }
    }

    private fun getAllPopularItemsFromFireStore() {
        _popularItemData.value = UiState.Loading

        viewModelScope.launch {
            try {
                _popularItemData.value = itemFireRepo.getPopularItemsList()
                Log.d("HomeViewModel", "Items fetched: $_popularItemData")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching items: ${e.message}")
            }
        }
    }
}