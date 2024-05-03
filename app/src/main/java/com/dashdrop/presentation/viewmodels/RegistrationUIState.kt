package com.dashdrop.presentation.viewmodels

import java.lang.Error

data class RegistrationUIState(
    var name: String = "",
    var email: String = "",
    var password: String = "",

    var nameError:Boolean=false,
    var emailError:Boolean=false,
    var passwordError: Boolean = false
)
