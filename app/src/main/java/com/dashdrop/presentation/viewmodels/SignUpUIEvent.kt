package com.dashdrop.presentation.viewmodels

sealed class SignUpUIEvent {

    data class NameChanged(val name:String) : SignUpUIEvent()
    data class EmailChanged(val email:String) : SignUpUIEvent()
    data class PasswordChanged(val password:String) : SignUpUIEvent()

    data object RegisterButtonClicked : SignUpUIEvent()
}