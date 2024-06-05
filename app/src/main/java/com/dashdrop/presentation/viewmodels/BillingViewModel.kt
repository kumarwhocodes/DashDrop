package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.repo.address.GetAddressFireRepo
import com.dashdrop.data.utils.UiState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillingViewModel @Inject constructor(
    private val addressFireRepo: GetAddressFireRepo
) : ViewModel(){

    private var billingUIStatePrivate = MutableStateFlow(BillingUIState())
    val billingUIState : StateFlow<BillingUIState> = billingUIStatePrivate

    private val db = Firebase.firestore
    private val user = Firebase.auth.currentUser

    init {
        loadAddressSize()
    }
    private fun loadAddressSize() {
        user?.uid?.let { uid ->
            db.collection("address").document(uid).get().addOnSuccessListener { document ->
                val size = document.get("addresses")?.let { addresses ->
                    (addresses as? List<*>)?.size ?: 0
                } ?: 0
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(addressId = size)
            }
        }
    }
    fun onEvent(event: BillingUIEvent){
        when(event){
            is BillingUIEvent.AddressChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    address = event.address
                )
            }
            is BillingUIEvent.AddressIdChanges -> {
                loadAddressSize()
            }
            is BillingUIEvent.CityChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    city = event.city
                )
            }
            is BillingUIEvent.CountryChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    country = event.country
                )
            }
            is BillingUIEvent.LandmarkChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    landmark = event.landmark
                )
            }
            is BillingUIEvent.LocalityChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    locality = event.locality
                )
            }
            is BillingUIEvent.NameChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    name = event.name
                )
            }
            is BillingUIEvent.PhoneNumberChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            is BillingUIEvent.PincodeChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    pinCode = event.pincode
                )
            }
            is BillingUIEvent.StateChanged -> {
                billingUIStatePrivate.value = billingUIStatePrivate.value.copy(
                    state = event.state
                )
            }
        }
    }

    private val _addressData: MutableStateFlow<UiState<List<DeliveryAddress>>> = MutableStateFlow(UiState.Idle)
    val addressData: StateFlow<UiState<List<DeliveryAddress>>> = _addressData.asStateFlow()

    fun getAllAddress() {
        _addressData.value = UiState.Loading

        viewModelScope.launch {
            try {
                val result = addressFireRepo.getAddress()
                _addressData.value = result
            } catch (e: Exception) {
                Log.e("BillingViewModel", "Error fetching address items: ${e.message}")
                _addressData.value = UiState.Error("Error fetching address items")
            }
        }
    }

}