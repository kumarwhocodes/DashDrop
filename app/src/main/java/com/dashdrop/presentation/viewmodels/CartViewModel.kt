package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashdrop.data.model.Cart
import com.dashdrop.data.repo.cart.GetCartFireRepo
import com.dashdrop.data.repo.cart.addCartInFireBase
import com.dashdrop.data.utils.UiState
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

    private val _cartData: MutableStateFlow<UiState<Pair<ArrayList<Cart>, Double>>> =
        MutableStateFlow(UiState.Idle)
    val cartData = _cartData.asStateFlow()

    private val _subtotal: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val subtotal: StateFlow<Double> = _subtotal.asStateFlow()

    private val _total: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total.asStateFlow()

    fun clearCart(){
        cartFireRepo.clearCart()
    }

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

    fun updateCartQuantity(itemId: String, operation: Boolean) {

        try {
            // Update Firestore
            addCartInFireBase(itemId = itemId, operation = operation)
            // Update local state
            val currentState = _cartData.value
            if (currentState is UiState.Success) {
                val (currentCart, total) = currentState.data
                val updatedCart = currentCart.mapNotNull { item ->
                    if (item.itemId == itemId) {
                        val newQuantity = if (operation) item.itemQuantity + 1 else item.itemQuantity - 1
                        if(newQuantity > 0){
                            item.copy(itemQuantity = newQuantity)
                        }else{
                            null
                        }
                    } else {
                        item
                    }
                }
                val newSubtotal = updatedCart.sumOf { it.itemPrice.toDouble() * it.itemQuantity }
                _cartData.value = UiState.Success(Pair(ArrayList(updatedCart), newSubtotal))
                _subtotal.value = newSubtotal
                _total.value = newSubtotal + 25.0
                Log.d("CartViewModel", "Cart $updatedCart")
                Log.d("CartViewModel", "Cart ${updatedCart.size}")
            }
        } catch (e: Exception) {
            Log.e("CartViewModel", "Error updating cart quantity: ${e.message}")
        }
    }
}