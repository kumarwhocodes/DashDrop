package com.dashdrop.presentation.screens

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
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController
) {
    val user = Firebase.auth.currentUser

    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Profile",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    navController.navigate(Screen.HomeScreen.route){
                        popUpTo(Screen.HomeScreen.route){inclusive = true}
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
                    itemValue = user?.displayName.toString()
                )

                ProfileScreenItem(
                    title = "Email",
                    icon = Icons.Filled.Mail,
                    itemValue = user?.email.toString()
                )

                ProfileScreenItem(
                    title = "Phone Number",
                    icon = Icons.Filled.Phone,
                    itemValue = user?.phoneNumber.toString()
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