package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.BottomNavBar
import com.dashdrop.ui.components.CategoryList
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.ImageSliderWithIndicator
import com.dashdrop.ui.components.ItemButton
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.components.SearchBox
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
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
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
                        .background(color = Color.White)
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