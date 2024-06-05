package com.dashdrop.presentation.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.ui.theme.bg
import com.dashdrop.ui.theme.rubikRegularStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    input: String,
    onInputChanged: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(15.dp)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(50.dp)
            )
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = input,
                onValueChange = onInputChanged,
                singleLine = true,
                label = {
                        Text(text = "Search your products",
                            fontFamily = rubikRegularStyle,
                            fontSize = 16.sp)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = MaterialTheme.colorScheme.secondary,
                    cursorColor = MaterialTheme.colorScheme.secondary,
                    focusedLabelColor = Color.Transparent,
                    unfocusedLabelColor = Color.Black.copy(0.5f),
                    focusedBorderColor = MaterialTheme.colorScheme.background,
                    unfocusedBorderColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearch("") }
                ),
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .padding(end = 2.dp)
                    .fillMaxWidth(1f),
                trailingIcon = {
                    FloatingActionButton(
                        onClick = {
                            onSearch("")
                        },
                        containerColor = Color.Transparent,
                        contentColor = bg,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search_fab",
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .size(40.dp)
                                .padding(end = 10.dp)
                        )
                    }
                })
        }

    }
}



@Preview(showBackground = true)
@Composable
private fun Preview() {

        SearchBox(input = "",
            onInputChanged = {}) {

        }


}