package com.dashdrop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.model.Payment

@Composable
fun PaymentItem(
    payment: Payment,
    isSelected: Boolean,
    onModeSelected: (Int) -> Unit
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
                    onClick = { onModeSelected(payment.paymentId) }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = payment.paymentMode)
                }

            }

        }
    }
}

@Composable
fun PaymentList(
    modes: List<Payment>
) {
    var selectedPaymentId by remember { mutableStateOf<Int?>(null) }

    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        modes.forEach { payment ->
            PaymentItem(payment = payment,
                isSelected = selectedPaymentId == payment.paymentId,
                onModeSelected = {selectedPaymentId == it})
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val modes = listOf(
        Payment(1, "Cash"),
        Payment(2, "Card"),
        Payment(3, "UPI")
    )
    PaymentList(modes = modes)
    
}