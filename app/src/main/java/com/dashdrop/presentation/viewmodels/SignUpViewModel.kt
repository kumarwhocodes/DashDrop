package com.dashdrop.presentation.viewmodels


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dashdrop.domain.models.toLocalUser
import com.dashdrop.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//TODO: NavGraph theek krna hai
class SignUpViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    val db = Firebase.firestore

    fun onEvent(event: SignUpUIEvent, navController: NavController) {
        validateDataWithRules()
        when (event) {
            is SignUpUIEvent.NameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    name = event.name
                )
            }

            is SignUpUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }

            is SignUpUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp(navController)
            }
        }
    }

    private fun signUp(navController: NavController) {
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password,
            navController
        )
    }

    private fun validateDataWithRules() {
        val nameResult = Validator.validateName(
            name = registrationUIState.value.name
        )
        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        registrationUIState.value = registrationUIState.value.copy(
            nameError = nameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status

        )
        allValidationsPassed.value =
            nameResult.status && emailResult.status && passwordResult.status
    }

    private fun createUserInFirebase(
        email: String,
        password: String,
        navController: NavController
    ) {
        signUpInProgress.value = true
        auth = Firebase.auth
        auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("mera_tag", "hogya create user")
                signUpInProgress.value = false
                if (it.isSuccessful) {
                    navController.navigate(Screen.HomeScreen.route){
                        popUpTo(Screen.HomeScreen.route){inclusive = true}
                    }
                    checkAndStoreUser(user = auth.currentUser)
                }
            }
            .addOnFailureListener {
                signUpInProgress.value=false
                Log.d("mera_tag", "nhi hua create")
            }


    }


}