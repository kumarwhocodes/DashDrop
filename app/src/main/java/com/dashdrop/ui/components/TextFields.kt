package com.dashdrop.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility

@Composable
fun HeadingText(value: String, size: TextUnit, weight: FontWeight) {
    Text(
        text = value,
        fontSize = size,
        fontWeight = weight,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(onTextSelected: (String)->Unit){
    val value = remember { mutableStateOf("") }
    OutlinedTextField(modifier = Modifier
        .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = value.value,
        singleLine = true,
        onValueChange = {
            value.value = it;
            onTextSelected(it)
        },
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor =  MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor =  MaterialTheme.colorScheme.primary,
            unfocusedTextColor =  MaterialTheme.colorScheme.secondary
        ))
}

@Composable
fun TextField_Text(labelValue: String) {
    Text(text = labelValue,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(onTextSelected: (String)->Unit)
{
    val value = remember { mutableStateOf("") }
    OutlinedTextField(modifier = Modifier
        .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = value.value,
        singleLine = true,
        onValueChange = {
            value.value = it;
            onTextSelected(it)
        },
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor =  MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor =  MaterialTheme.colorScheme.primary,
            unfocusedTextColor =  MaterialTheme.colorScheme.secondary
        ))
}

@Composable
fun DividerTextComponent(value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = value,
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(onTextSelected: (String) -> Unit) {

    val localFocusManager = LocalFocusManager.current

    val password = remember { mutableStateOf("") }

    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        value = password.value,
        onValueChange = {
            password.value = it;
            onTextSelected(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
        ),
        trailingIcon = {

            val iconImage = if(passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }

            val description = if(passwordVisible.value){
                "Hide Password"
            }else{
                "Show Password"
            }

            IconButton(onClick = {
                passwordVisible.value = !passwordVisible.value
            }) {
                Icon(
                    imageVector = iconImage,
                    contentDescription = description
                )
            }

        },
        visualTransformation = if(passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),

    )
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column (modifier = Modifier.padding(20.dp)){
        TextField_Text(labelValue = "Your Name")
        CustomTextField( onTextSelected = {})
        TextField_Text(labelValue = "Email Address")
        CustomInputField(onTextSelected = {})
        TextField_Text(labelValue = "Enter Your Password")
        PasswordTextField(onTextSelected = {})
        DividerTextComponent(value = "or sign up with")
    }

}