package com.dashdrop.domain.models

import com.google.firebase.auth.FirebaseUser

data class LocalUser(
    val name: String?,
    val username: String?,
    val uid: String,
    val email: String,
    val photoUrl: String?,
    val phone_number: String?,
    val password: String?
)

fun FirebaseUser.toLocalUser(): LocalUser {
    return LocalUser(
        name = displayName,
        uid = uid,
        username = displayName,
        email = email ?: "",
        photoUrl = photoUrl?.toString(),
        phone_number = phoneNumber,
        password = null
    )
}