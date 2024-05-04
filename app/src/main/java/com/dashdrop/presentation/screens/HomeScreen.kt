package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.viewmodels.SignUpUIEvent
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.PrimaryButton

@Composable
fun HomeScreen(signInViewModel: SignInViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeadingText(value = "Home Screen",
                size = 32.sp,
                weight = FontWeight.Bold ,
                color = Color.Black)

            PrimaryButton(onClick = {
                signInViewModel.logout()
            }) {
                Text(text = "Logout")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HomeScreen()
}