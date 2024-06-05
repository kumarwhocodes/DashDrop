package com.dashdrop.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInUIEvent
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.viewmodels.checkAndStoreUser
import com.dashdrop.presentation.components.core.ClickableLoginTextComponent
import com.dashdrop.presentation.components.core.CustomInputField
import com.dashdrop.presentation.components.core.DividerTextComponent
import com.dashdrop.presentation.components.core.HeadingText
import com.dashdrop.presentation.components.core.LoginButton
import com.dashdrop.presentation.components.core.PasswordTextField
import com.dashdrop.presentation.components.core.TextField_Text
import com.dashdrop.ui.theme.bg
import com.dashdrop.ui.theme.rubikBoldStyle
import com.dashdrop.ui.theme.rubikRegularStyle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController
) {
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val context = LocalContext.current
    val token = stringResource(id = R.string.web_client_id)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                        color = Color.White,
                        font = rubikBoldStyle
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    HeadingText(
                        value = stringResource(id = R.string.Sign_In_Info),
                        size = 16.sp,
                        color = Color.White,
                        font = rubikRegularStyle
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

                            TextField_Text(
                                labelValue = stringResource(id = R.string.Email_Address)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            CustomInputField(onTextSelected = {
                                signInViewModel.onEvent(
                                    SignInUIEvent.EmailChanged(it),
                                    navController
                                )
                            }, errorStatus = signInViewModel.loginUIState.value.emailError)

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField_Text(
                                labelValue = stringResource(id = R.string.Password)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            PasswordTextField(
                                onTextSelected = {
                                    signInViewModel.onEvent(
                                        SignInUIEvent.PasswordChanged(it),
                                        navController
                                    )
                                },
                                errorStatus = signInViewModel.loginUIState.value.passwordError
                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            LoginButton(
                                value = stringResource(id = R.string.Sign_In),
                                onClick = {
                                    signInViewModel.onEvent(
                                        SignInUIEvent.LoginButtonClicked,
                                        navController
                                    )
                                },
                                isEnabled = signInViewModel.allValidationsPassed.value
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(thickness = 1.dp, color = Color.Black)
                            Spacer(modifier = Modifier.height(20.dp))
                            ClickableLoginTextComponent(
                                tryingToLogin = false,
                                onTextSelected = {
                                    navController.navigate(Screen.SignUpScreen.route)
                                }
                            )

                            Spacer(modifier = Modifier.height(90.dp))

                            DividerTextComponent(value = stringResource(id = R.string.Other_Sign_In))

                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                GoogleSignInButton(
                                    context = context,
                                    onAuthComplete = { result ->
                                        user = result.user
                                        navController.navigate(Screen.HomeScreen.route)
                                        Toast.makeText(context,"Sign In Successful",Toast.LENGTH_SHORT).show()
                                        checkAndStoreUser(user)
                                    },
                                    onAuthError = {
                                        user = null
                                        Toast.makeText(context,"Sign Up Failed",Toast.LENGTH_SHORT).show()
                                    },
                                    token = token
                                )
                            }
                        }
                    }


                }


            }
        }

        if (signInViewModel.loginInProgress.value) {
            CircularProgressIndicator(color = bg)
        }
    }


}