package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dashdrop.data.utils.Validator
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

    fun onEvent(event: SignInUIEvent, navController: NavController) {
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
                login(loginUIState.value.email, loginUIState.value.password,navController)
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
        password: String,
        navController: NavController
    ) {
        loginInProgress.value = true
        auth = Firebase.auth
        auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("mera_tag", "hogya login")
                loginInProgress.value = false
                if (it.isSuccessful) {
                    navController.navigate(Screen.HomeScreen.route){
                        popUpTo(Screen.HomeScreen.route){inclusive = true}
                    }
                    checkAndStoreUser(user = auth.currentUser)
                }
                else {
                    // Show a failure toast message.

                }
            }
            .addOnFailureListener {
                loginInProgress.value = false
                Log.d("mera_tag", "nhi hua login")
            }
    }

    fun logout(navController: NavController) {
        auth = Firebase.auth
        auth.signOut()

        val authStateListener = AuthStateListener {
            if (it.currentUser == null) {
                Log.d("mera_tag", "hogya logout")
                navController.navigate(Screen.SignUpScreen.route)
            } else {
                Log.d("mera_tag", "nhi hua logout")
            }
        }

        auth.addAuthStateListener(authStateListener)
    }

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

}