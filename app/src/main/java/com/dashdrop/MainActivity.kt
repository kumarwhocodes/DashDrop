package com.dashdrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dashdrop.navigation.DashDropNavGraph
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.presentation.viewmodels.SignUpViewModel
import com.dashdrop.ui.theme.DashDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DashDropTheme {
                DashDropNavGraph(
                    signInViewModel = SignInViewModel(),
                    signUpViewModel = SignUpViewModel(),
                    isUserLoggedIn = false
                )
            }
        }
    }
}
