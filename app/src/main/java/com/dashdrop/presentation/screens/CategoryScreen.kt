package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dashdrop.R
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.AppToolBar
import com.dashdrop.ui.components.ItemButton

@Composable
fun CategoryScreen(signInViewModel: SignInViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            AppToolBar(toolbarTitle = "Veggies",
                logOutButtonClicked = {
                    signInViewModel.logout()
                },
                navigationIconClicked = {
                    /*TODO: Back Button Logic*/
                })
        },
        bottomBar = {
            /*TODO: Bottom Navigation Bar*/
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            LazyColumn() {
                items(5){
                    Row(){
                        ItemButton(
                            value = "Veggies",
                            image = painterResource(id = R.drawable.veggiess),
                            price = "150",
                            startCount = 2.0
                        )
                        ItemButton(
                            value = "Veggies",
                            image = painterResource(id = R.drawable.veggiess),
                            price = "150",
                            startCount = 3.0
                        )
                    }
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CategoryScreen()
}