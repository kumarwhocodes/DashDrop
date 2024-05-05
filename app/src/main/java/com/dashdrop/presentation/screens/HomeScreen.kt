package com.dashdrop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dashdrop.R
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.AppToolBar
import com.dashdrop.ui.components.CategoryButton
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.ItemBanner
import com.dashdrop.ui.components.ItemButton
import com.dashdrop.ui.components.SearchBox
import com.dashdrop.ui.theme.bg

@Composable
fun HomeScreen(signInViewModel: SignInViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            AppToolBar(toolbarTitle = "Home Screen",
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

                    SearchBox(input = "Search your products",
                        onInputChanged = {}) {
                        /*TODO: Search Button Logic*/
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp)
                        .background(color = Color.White)){
                    ItemBanner(image = painterResource(id = R.drawable.food),
                        contentDescription = "GET FLAT 30% OFF ON GROCERIES!!")

                    Spacer(modifier = Modifier
                        .height(20.dp))

                    HeadingText(value = "Categories",
                        size = 24.sp,
                        weight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                    )

                    Row {
                        //TODO: Implement LazyRow here
                        CategoryButton(value = "Veggies",
                            image = painterResource(id = R.drawable.veggiess),
                            imageDesc = null)
                        CategoryButton(value = "Veggies",
                            image = painterResource(id = R.drawable.veggiess),
                            imageDesc = null)
                        CategoryButton(value = "Veggies",
                            image = painterResource(id = R.drawable.veggiess),
                            imageDesc = null)
                        CategoryButton(value = "Veggies",
                            image = painterResource(id = R.drawable.veggiess),
                            imageDesc = null)
                        CategoryButton(value = "Veggies",
                            image = painterResource(id = R.drawable.veggiess),
                            imageDesc = null)

                    }

                    Spacer(modifier = Modifier
                        .height(20.dp))

                    HeadingText(value = "Popular",
                        size = 24.sp,
                        weight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                    )

                    ItemButton(value = "Veggies",
                        image = painterResource(id = R.drawable.veggiess),
                        price = "150")

                    //TODO: Here, item button ka size undefined karo taaki wo grid m apna size khud hi le lega


                }



            }
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HomeScreen()
}