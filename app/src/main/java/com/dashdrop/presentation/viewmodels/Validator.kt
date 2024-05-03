package com.dashdrop.presentation.viewmodels

object Validator {

    fun validateName(name: String): ValidationResult {
        return ValidationResult(
            (!name.isNullOrEmpty())
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length > 5)
        )
    }

    data class ValidationResult(
        val status: Boolean = false
    )

}