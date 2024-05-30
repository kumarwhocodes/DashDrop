package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.data.model.Cart
import com.dashdrop.data.model.Category
import com.dashdrop.data.repo.GetCartFireRepo
import com.dashdrop.data.utils.UiState
import com.dashdrop.fireStore.addCartinFireBase
import com.dashdrop.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartFireRepo: GetCartFireRepo
) : ViewModel() {

    private val _cartData: MutableStateFlow<UiState<Pair<ArrayList<Cart>,Double>>> = MutableStateFlow(UiState.Idle)
    val cartData = _cartData.asStateFlow()

    private val _subtotal: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val subtotal: StateFlow<Double> = _subtotal.asStateFlow()

    private val _total: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _subtotal.asStateFlow()

    fun getAllCart() {
        _cartData.value = UiState.Loading

        viewModelScope.launch {
            try {
                val result = cartFireRepo.getCartList()
                _cartData.value = result
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error fetching cart items: ${e.message}")
                _cartData.value = UiState.Error("Error fetching cart items")
            }
        }
    }

    fun updateCartQuantity(itemId: String, operation: Boolean,navController : NavController): Double {
        val currentState = _cartData.value
        if (currentState is UiState.Success) {
            val (currentCart, total) = currentState.data
            val updatedCart = currentCart.map { item ->
                if (item.item_id == itemId) {
                    val newQuantity = if (operation) item.item_quantity + 1 else item.item_quantity - 1
                    item.copy(item_quantity = newQuantity.coerceAtLeast(0))
                } else {
                    item
                }
            }
            val newSubtotal = updatedCart.sumOf { it.item_price.toDouble() * it.item_quantity }
            _cartData.value = UiState.Success(Pair(ArrayList(updatedCart), total))
            _subtotal.value = newSubtotal

            val newTotal = newSubtotal + 25
            _total.value = newTotal

            return newSubtotal
        }

        // Remote update
        viewModelScope.launch {
            try {
                addCartinFireBase(
                    itemId = itemId,
                    operation = operation
                )
            } catch (e: Exception) {
                Log.d("CartViewModel", "updateCartQuantity: ${e.message}")
            }
        }
        return _subtotal.value
    }

}