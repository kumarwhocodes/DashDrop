package com.dashdrop.presentation.components.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dashdrop.ui.theme.PrimaryColor

@Composable
fun ProfileScreenItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    itemValue: String?="null"
) {
    Column(modifier = modifier
        .padding(8.dp)) {
        TextField_Text(
            labelValue = title
        )
        Card(
            modifier=modifier,
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(),
            border = BorderStroke(2.dp, PrimaryColor)
        ) {
            Surface{
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = itemValue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = itemValue!!,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }
        }
    }

}