package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dashdrop.data.model.Category
import com.dashdrop.data.model.Item
import com.dashdrop.data.repo.GetCategoryFireRepo
import com.dashdrop.data.repo.GetItemFireRepo
//import com.dashdrop.data.repo.ServiceLocator
import com.dashdrop.data.utils.UiState
import com.dashdrop.navigation.Screen
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

    fun getAllCategory() {
        Log.d("HomeViewModel", "getAllCategory called")
        getAllCategoryFromFireStore()

    }

    fun getAllItems(path: String, navController: NavController) {
        Log.d("HomeViewModel", "getAllItems called with path: $path")
        getAllItemsFromFireStore(path, navController)

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

    private fun getAllItemsFromFireStore(path: String, navController: NavController) {
        _itemData.value = UiState.Loading
        Log.d("HomeViewModel", "Fetching items from Firestore for path: $path")

        viewModelScope.launch {
            try {
                _itemData.value = itemFireRepo.getItemList(path, navController)
                Log.d("HomeViewModel", "Items fetched: $_itemData")
                if (_itemData.value is UiState.Success) {
                    navController.navigate("category/$path")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching items: ${e.message}")
            }
        }
    }
}