package com.dashdrop.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.BottomNavBar
import com.dashdrop.ui.components.ProfileScreenItem
import com.dashdrop.ui.components.ScaffoldTop
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController
) {
    val user = Firebase.auth.currentUser

    val db = Firebase.firestore

    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userPhoneNumber by remember { mutableStateOf("") }

    LaunchedEffect(user) {
        user?.let {
            db.collection("users").document(it.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        userName = document.getString("name").orEmpty()
                        userEmail = document.getString("email").orEmpty()
                        userPhoneNumber = document.getString("phoneNumber").orEmpty()
                    }
                    else{
                        Log.d("mera_tag","data hi nhi hai firebase pe")
                    }
                }
                .addOnFailureListener {
                    Log.d("mera_tag", "data hi nhi hai firebase pe")
                }
        }
    }


    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Profile",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                    }
                })
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = user?.photoUrl,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = stringResource(id = R.string.Profile),
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                )


                ProfileScreenItem(
                    title = "Name",
                    icon = Icons.Filled.Person,
                    itemValue = userName
                )

                ProfileScreenItem(
                    title = "Email",
                    icon = Icons.Filled.Mail,
                    itemValue = userEmail
                )

                ProfileScreenItem(
                    title = "Phone Number",
                    icon = Icons.Filled.Phone,
                    itemValue = userPhoneNumber
                )
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    ProfileScreen(
        navController = rememberNavController()

    )

}