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

    fun validateAddress(
        name: String,
        phoneNumber: String,
        address: String,
        city: String,
        state: String,
        country: String
    ): ValidationResult {
        return ValidationResult(
            (name.isNotEmpty() && phoneNumber.isNotEmpty() && address.isNotEmpty() && city.isNotEmpty() && state.isNotEmpty() && country.isNotEmpty())
        )
    }

    fun validatePinCode(
        pinCode: Int
    ): ValidationResult {
        return ValidationResult(
            (pinCode.toString().length == 6)
        )
    }

}