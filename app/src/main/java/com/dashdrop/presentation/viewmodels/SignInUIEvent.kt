package com.dashdrop.presentation.viewmodels

sealed class SignInUIEvent {

    data class EmailChanged(val email:String) : SignInUIEvent()
    data class PasswordChanged(val password:String) : SignInUIEvent()

    data object LoginButtonClicked : SignInUIEvent()
}