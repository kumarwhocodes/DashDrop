package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashdrop.data.model.Cart
import com.dashdrop.data.model.Category
import com.dashdrop.data.model.Favourite
import com.dashdrop.data.repo.GetCartFireRepo
import com.dashdrop.data.repo.GetFavFireRepo
import com.dashdrop.data.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val favFireRepo: GetFavFireRepo
) : ViewModel() {

    private val _favData: MutableStateFlow<UiState<ArrayList<Favourite>>> = MutableStateFlow(UiState.Idle)
    val favData = _favData.asStateFlow()

    fun getAllFav() {
        _favData.value = UiState.Loading

        viewModelScope.launch {
            try {
                val result = favFireRepo.getFavList()
                _favData.value = result
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error fetching cart items: ${e.message}")
                _favData.value = UiState.Error("Error fetching cart items")
            }
        }
    }

}