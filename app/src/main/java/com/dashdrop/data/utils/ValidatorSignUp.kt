package com.dashdrop.data.utils

object ValidatorSignUp {

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

object ValidatorAddress{
    data class ValidationResult(
        val status: Boolean = false
    )

    fun validateCountry(country: String): ValidationResult {
        return ValidationResult(
            (country.isNotEmpty())
        )
    }

    fun validateFullName(fullName: String): ValidationResult {
        return ValidationResult(
            (fullName.isNotEmpty())
        )
    }

    fun validateMobile(mobile: String): ValidationResult {
        return ValidationResult(
            (mobile.isNotEmpty())
        )
    }

    fun validateAddress(address: String): ValidationResult {
        return ValidationResult(
            (address.isNotEmpty())
        )
    }

    fun validateLocality(locality: String): ValidationResult {
        return ValidationResult(
            (locality.isNotEmpty())
        )
    }

    fun validateLandmark(landmark: String): ValidationResult {
        return ValidationResult(
            (landmark.isNotEmpty())
        )
    }

    fun validatePincode(pincode: String): ValidationResult {
        return ValidationResult(
            (pincode.isNotEmpty())
        )
    }

    fun validateCity(city: String): ValidationResult {
        return ValidationResult(
            (city.isNotEmpty())
        )
    }

    fun validateState(state: String): ValidationResult {
        return ValidationResult(
            (state.isNotEmpty())
        )
    }

}