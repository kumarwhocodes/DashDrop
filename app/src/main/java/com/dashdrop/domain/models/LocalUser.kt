package com.dashdrop.domain.models

import com.google.firebase.auth.FirebaseUser

data class LocalUser(
    val userName: String?,
    val uid: String,
    val email: String,
    val photoUrl: String?,
    val phoneNumber: String?
)

fun FirebaseUser.toLocalUser(): LocalUser {
    return LocalUser(
        uid = uid,
        userName = displayName,
        email = email ?: "",
        photoUrl = photoUrl?.toString(),
        phoneNumber = phoneNumber
    )
}