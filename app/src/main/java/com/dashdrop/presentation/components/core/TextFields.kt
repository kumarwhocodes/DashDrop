package com.dashdrop.presentation.components.core

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.ui.theme.rubikSemiBoldStyle

@Composable
fun HeadingText(
    value: String,
    size: TextUnit,
    color: Color,
    style: TextStyle = TextStyle.Default,
    textAlign: TextAlign = TextAlign.Center,
    lineHeight: TextUnit = TextUnit.Unspecified,
    font: FontFamily
) {
    Text(
        text = value,
        fontSize = size,
        fontFamily = font,
        color = color,
        textAlign=textAlign,
        style = style,
        lineHeight = lineHeight
    )
}


@Composable
fun TextField_Text(labelValue: String) {
    Text(
        text = labelValue,
        fontSize = 16.sp,
        color = Color.Black,
        fontFamily = rubikSemiBoldStyle
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val value = remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = value.value,
        singleLine = true,
        onValueChange = {
            value.value = it
            onTextSelected(it)
        },
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ), isError = !errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    action: ImeAction = ImeAction.Next
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            fontFamily = rubikSemiBoldStyle
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(imeAction = action),
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = Color.Black,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            )
        )
    }
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
fun PasswordTextField(
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean
) {

    val localFocusManager = LocalFocusManager.current

    val password = remember { mutableStateOf("") }

    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
        ),
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                "Hide Password"
            } else {
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
        visualTransformation = if (passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        isError = !errorStatus
    )
}


@Composable
fun ClickableLoginTextComponent(
    tryingToLogin: Boolean = true,
    onTextSelected: (String) -> Unit
) {

    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        text = annotatedString,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center
        ),
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{$span}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        })
}