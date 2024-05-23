package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dashdrop.data.model.Cart
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.repo.GetAddressFireRepo
import com.dashdrop.data.utils.UiState
import com.dashdrop.presentation.viewmodels.BillingViewModel

@Composable
fun AddressItem(
    address: DeliveryAddress,
    isSelected: Boolean,
    onAddressSelected: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(if (isSelected) Color.LightGray else Color.White)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { onAddressSelected(address.addressId) }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = address.name)
                    Text(text = "${address.address}, ${address.city}, ${address.state} ${address.pincode}")
                }

            }

        }
    }
}

@Composable
fun AddressList(
    billingViewModel: BillingViewModel = hiltViewModel()
) {
    val addressData by billingViewModel.addressData.collectAsState()

    when (addressData) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
        is UiState.Success -> {
            val addressList = (addressData as UiState.Success<List<DeliveryAddress>>).data
            if (addressList.isNotEmpty()) {
                Column {
                    addressList.forEach { address ->
                        AddressItem(
                            address = address,
                            isSelected = false, // Handle selection state as per your requirement
                            onAddressSelected = { /* Handle address selection */ }
                        )
                    }
                }
            }
        }
        else -> {
            billingViewModel.getAllAddress()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    Column {
        val addresses = listOf(
            DeliveryAddress(
                addressId = 1,
                name = "Kumar Sambhav",
                phoneNumber = "6207549371",
                city = "Madhubani",
                state = "Bihar",
                pincode = "847234",
                locality = "Pandaul",
                address = "Pandaul",
                country = "India"
            ),
            DeliveryAddress(
                addressId = 2,
                name = "Kumar Sambhav",
                phoneNumber = "6207549371",
                city = "Madhubani",
                state = "Bihar",
                pincode = "847234",
                locality = "Pandaul",
                address = "Pandaul",
                country = "India"
            ),
            DeliveryAddress(
                addressId = 3,
                name = "Kumar Sambhav",
                phoneNumber = "6207549371",
                city = "Madhubani",
                state = "Bihar",
                pincode = "847234",
                locality = "Pandaul",
                address = "Pandaul",
                country = "India"
            )
        )
        AddressList()
    }
}