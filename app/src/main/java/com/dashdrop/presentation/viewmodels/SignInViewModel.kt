package com.dashdrop.presentation.viewmodels


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//TODO: Signup krne k baad on complete listener k baad on failure wala bhi call horha hai
//TODO: After signup, navigation ni horha
//TODO: First page signin wala set tha usko ham signup kiye hai...already have and account wala clickable button bnane k baad wapas se start destination ko signin wala krdena

class SignInViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    fun onEvent(event: UIEvent) {
        validateDataWithRules()
        when (event) {
            is UIEvent.NameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    name = event.name
                )
            }

            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }

            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
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
        password: String
    ) {
        auth = Firebase.auth
        auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("mera_tag", "hogya create user")
            }
            .addOnFailureListener {
                Log.d("mera_tag", "nhi hua create")
            }


    }


}