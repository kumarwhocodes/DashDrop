package com.dashdrop.presentation.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import com.dashdrop.R
import com.dashdrop.presentation.components.core.SmallCircularImageButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun GoogleSignInButton(
    context: Context,
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit,
    token: String
) {
    val googleSignInClient = rememberGoogleSignInClient(context, token)
    val launcher = rememberFirebaseAuthLauncher(onAuthComplete, onAuthError)

    SmallCircularImageButton(
        onClick = {
            googleSignInClient.signOut().addOnCompleteListener {
                launcher.launch(googleSignInClient.signInIntent)
            }
        },
        image = painterResource(id = R.drawable.google),
        desc = "google"
    )
}

@Composable
fun rememberGoogleSignInClient(context: Context, token: String): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(token)
        .requestEmail()
        .requestProfile()
        .requestId()
        .build()

    return GoogleSignIn.getClient(context, gso)
}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()

    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            Log.d("mera_tag", "account $account")

            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)

            scope.launch {
                val authResult = FirebaseAuth.getInstance().signInWithCredential(credential).await()
                onAuthComplete(authResult)
                Log.d("mera_tag", "${account.displayName} has logged in")
            }
        } catch (e: ApiException) {
            Log.d("mera_tag", "$e Google sign-in failed")
            onAuthError(e)
        }
    }
}
