package com.dashdrop.presentation.viewmodels


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dashdrop.data.model.LocalUser
import com.dashdrop.data.utils.Validator
import com.dashdrop.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    private val db = Firebase.firestore

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
            navController = navController
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
            .addOnCompleteListener { it ->
                Log.d("mera_tag", "hogya create user")
                signUpInProgress.value = false
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userInfo = LocalUser(
                            name = registrationUIState.value.name,
                            uid = it.uid,
                            username = registrationUIState.value.name,
                            email = email,
                            phoneNumber = null
                        )
                        db.collection("users").document(it.uid)
                            .set(userInfo)
                            .addOnSuccessListener {
                                Log.d("mera_tag", "User ka naam aur email hogya store")
                                navController.navigate(Screen.HomeScreen.route) {
                                    popUpTo(Screen.HomeScreen.route) { inclusive = true }

                                    navController.navigate(Screen.HomeScreen.route) {
                                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                                    }
                                    checkAndStoreUser(user = auth.currentUser)
                                }
                            }
                            .addOnFailureListener {
                                signUpInProgress.value = false
                                Log.d("mera_tag", "nhi hua create")
                            }

                        val cartData = hashMapOf(
                            "item_id" to arrayListOf<String>(),
                            "item_quantity" to arrayListOf<String>()
                        )
                        val favouriteData = hashMapOf(
                            "item_id" to arrayListOf<String>()
                        )

                        FirebaseFirestore.getInstance()
                            .collection("cart")
                            .document(user.uid)
                            .set(cartData)
                            .addOnSuccessListener {
                                Log.d("mera_tag", "firestore me cart document store hogya")
                            }
                            .addOnFailureListener {
                                Log.d("mera_tag", "firestore me cart document store NAHI hua")
                            }

                        FirebaseFirestore.getInstance()
                            .collection("favourite")
                            .document(user.uid)
                            .set(favouriteData)
                            .addOnSuccessListener {
                                Log.d("mera_tag", "firestore me cart document store hogya")
                            }
                            .addOnFailureListener {
                                Log.d("mera_tag", "firestore me cart document store NAHI hua")
                            }

                        val addressData = hashMapOf("addresses" to arrayListOf<String>())

                        FirebaseFirestore.getInstance()
                            .collection("address")
                            .document(user.uid)
                            .set(addressData)
                            .addOnSuccessListener {
                                Log.d("mera_tag", "firestore me address document store hogya")
                            }
                            .addOnFailureListener {
                                Log.d("mera_tag", "firestore me address document store NAHI hua")
                            }
                    }
                } else {
                    Log.d("mera_tag", "nhi hua create user email se")
                }
            }
            .addOnFailureListener {
                signUpInProgress.value = false
                Log.d("mera_tag", "nhi hua create user email se", it)
            }

    }
}



