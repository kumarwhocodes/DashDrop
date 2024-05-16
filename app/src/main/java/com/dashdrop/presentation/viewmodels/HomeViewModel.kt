package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dashdrop.data.model.Category
import com.dashdrop.data.model.Item
import com.dashdrop.data.repo.GetFireRepo
import com.dashdrop.data.utils.UiState
import com.dashdrop.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel(private val repository: GetFireRepo) :ViewModel() {
    private val _categoryData: MutableStateFlow<UiState<ArrayList<Category>>> = MutableStateFlow(UiState.Idle)
    val categoryData = _categoryData.asStateFlow()

    private val _itemData: MutableStateFlow<UiState<ArrayList<Item>>> = MutableStateFlow(UiState.Idle)
    val itemData = _itemData.asStateFlow()

    fun getAllCategory() {
        getAllCategoryFromFireStore()
    }

    fun getAllItems(path: String, navController: NavController){
        getAllItemsFromFireStore(path,navController)
    }

    private fun getAllCategoryFromFireStore() {
        _categoryData.value=UiState.Loading

        viewModelScope.launch {
            try {
                _categoryData.value=repository.getCategoryList()
            }
            catch (e:Exception){
                Log.e("Error", e.message.toString())
            }
        }
    }

    private fun getAllItemsFromFireStore(path: String, navController: NavController) {
        _itemData.value=UiState.Loading

        viewModelScope.launch {
            try {
                _itemData.value=repository.getItemList(path,navController)
                if(_itemData.value is UiState.Success){
                    navController.navigate(Screen.CategoryScreen.route)
                }
            }
            catch (e:Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}