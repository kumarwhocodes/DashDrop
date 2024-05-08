package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dashdrop.navigation.HOME_GRAPH_ROUTE
import com.dashdrop.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: SignInUIEvent) {
        validateLoginUIDataWithRules()
        when (event) {
            is SignInUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is SignInUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is SignInUIEvent.LoginButtonClicked -> {
                login(loginUIState.value.email, loginUIState.value.password)
            }
        }
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }


    private fun login(
        email: String,
        password: String
    ) {
        loginInProgress.value = true
        auth = Firebase.auth
        auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("mera_tag", "hogya login")
                loginInProgress.value = false
                if (it.isSuccessful) {
                    //Toast
                }
            }
            .addOnFailureListener {
                loginInProgress.value = false
                Log.d("mera_tag", "nhi hua login")
            }
    }

    fun logout() {
        auth = Firebase.auth
        auth.signOut()

        val authStateListener = AuthStateListener {
            if (it.currentUser == null) {
                Log.d("mera_tag", "hogya logout")
            } else {
                Log.d("mera_tag", "nhi hua logout")
            }
        }

        auth.addAuthStateListener(authStateListener)
    }
}