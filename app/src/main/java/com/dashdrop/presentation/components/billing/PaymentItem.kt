package com.dashdrop.presentation.components.billing

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dashdrop.data.model.Payment
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.cardBackgroundColor
import com.dashdrop.ui.theme.cardIconBackgroundColor

@Composable
fun PaymentItem(
    payment: Payment,
    isSelected: Boolean,
    onModeSelected: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .size(177.dp, 191.dp)
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(cardBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp,14.dp,4.dp,4.dp),
                modifier = Modifier
                    .background(Color.Transparent)
                    .size(171.dp,86.dp)
            ){
                AsyncImage(
                    model = payment.paymentImage,
                    contentDescription = payment.paymentMode,
                    modifier = Modifier
                        .background(cardIconBackgroundColor)
                        .padding(2.dp)
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Column {
                Text(text = payment.paymentMode)
            }
            Spacer(modifier = Modifier.height(5.dp))
            RadioButton(
                selected = isSelected,
                onClick = { onModeSelected() },
                colors = androidx.compose.material3.RadioButtonDefaults.colors(
                    selectedColor = PrimaryColor,
                )
            )
        }
    }
}

@Composable
fun PaymentList(
    modes: List<Payment>,
    onPaymentSelected: (Payment) -> Unit
) {
    var selectedPaymentIndex by remember { mutableIntStateOf(-1) }

    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        modes.forEachIndexed { index, payment ->
            PaymentItem(payment = payment,
                isSelected = index == selectedPaymentIndex,
                onModeSelected = {
                    selectedPaymentIndex = index
                    onPaymentSelected(payment)
                }
            )
        }
    }
}