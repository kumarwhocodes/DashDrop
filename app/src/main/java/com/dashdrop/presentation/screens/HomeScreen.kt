package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.BottomNavBar
import com.dashdrop.ui.components.CategoryList
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.components.SearchBox
import com.dashdrop.ui.theme.backgroundColor
import com.dashdrop.ui.theme.bg

@Composable
fun HomeScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController,
    onBackPressed: () -> Unit = {},
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            ScaffoldTop(toolbarTitle = "Home Screen",
                logOutButtonClicked = {
                    signInViewModel.logout(navController)
                },
                navigationIconClicked = {
                    onBackPressed()
                })
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = bg)
                ) {

                    SearchBox(input = "",
                        onInputChanged = {}) {
                        /*TODO: Search Button Logic*/
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(5.dp)
                        .background(color = backgroundColor)
                ) {
                    CategoryList(navController = navController, homeViewModel = homeViewModel)


                }


            }
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HomeScreen(
        navController = rememberNavController()
    )
}