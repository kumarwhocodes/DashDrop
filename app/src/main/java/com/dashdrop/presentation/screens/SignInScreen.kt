package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dashdrop.R
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.viewmodels.UIEvent
import com.dashdrop.ui.components.CustomInputField
import com.dashdrop.ui.components.DividerTextComponent
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.LoginButton
import com.dashdrop.ui.components.PasswordTextField
import com.dashdrop.ui.components.SmallCircularImageButton
import com.dashdrop.ui.components.TextField_Text
import com.dashdrop.ui.theme.bg

//TODO: New to here? Register wala button add krna hai
@Composable
fun SignInScreen(signInViewModel: SignInViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bg)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(
            modifier = Modifier.padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            HeadingText(
                value = stringResource(id = R.string.Sign_In),
                size = 30.sp,
                weight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            HeadingText(
                value = stringResource(id = R.string.Sign_In_Info),
                size = 16.sp,
                weight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(50.dp))
        }

        Card(
            modifier = Modifier
                .padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(10.dp)
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 10.dp, bottom = 10.dp,
                            start = 5.dp, end = 5.dp
                        )
                        .fillMaxSize()
                        .padding(12.dp, 0.dp),
                    verticalArrangement = Arrangement.Top,

                    ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField_Text(labelValue = stringResource(id = R.string.Email_Address))
                    Spacer(modifier = Modifier.height(5.dp))
                    CustomInputField(onTextSelected = {
                        signInViewModel.onEvent(UIEvent.EmailChanged(it))
                    }, errorStatus = signInViewModel.registrationUIState.value.emailError)
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField_Text(labelValue = stringResource(id = R.string.Password))
                    Spacer(modifier = Modifier.height(5.dp))
                    PasswordTextField(
                        onTextSelected = {
                            signInViewModel.onEvent(UIEvent.PasswordChanged(it))
                        },
                        errorStatus = signInViewModel.registrationUIState.value.passwordError
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    LoginButton(
                        value = stringResource(id = R.string.Sign_In),
                        onClick = {

                        },
                        isEnabled = true
                    )


                    Spacer(modifier = Modifier.height(140.dp))

                    DividerTextComponent(value = stringResource(id = R.string.Other_Sign_In))

                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        SmallCircularImageButton(
                            onClick = { },
                            image = painterResource(id = R.drawable.google),
                            desc = "google_icon"
                        )
                    }
                }
            }


        }


    }
}

@Composable
@Preview(showSystemUi = true)
fun SignInScreen_Preview() {
    SignInScreen()
}