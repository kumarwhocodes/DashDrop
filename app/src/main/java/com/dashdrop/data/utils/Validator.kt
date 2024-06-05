package com.dashdrop.data.utils

object Validator {

    data class ValidationResult(
        val status: Boolean = false
    )

    fun validateName(name: String): ValidationResult {
        return ValidationResult(
            (name.isNotEmpty())
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length > 5)
        )
    }

}