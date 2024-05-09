package com.dashdrop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignInViewModel
import com.dashdrop.ui.components.DetailsImage
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.PrimaryButton
import com.dashdrop.ui.components.ScaffoldTop
import com.dashdrop.ui.components.StarsRow
import com.dashdrop.ui.theme.bg

@Composable
fun DetailsScreen(
    signInViewModel: SignInViewModel = viewModel(),
    navController: NavController
) {
    Scaffold(modifier = Modifier, topBar = {
        ScaffoldTop(
            toolbarTitle = "Details",
            logOutButtonClicked = {
                signInViewModel.logout(navController)
            },
            navigationIconClicked = {
                navController.popBackStack(Screen.CategoryScreen.route, inclusive = false)
            },
            containerColor = bg.copy(0.25f),
            contentColor = Color.Black.copy(0.75f),
            iconColor = bg
        )
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
            ) {
                DetailsImage(
                    image = painterResource(R.drawable.veggiess),
                    imagedesc = "Veggies",
                    size = 280.dp,
                    color = bg.copy(0.25f)
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    HeadingText(
                        modifier = Modifier,
                        value = "Fresh Orange",
                        size = 32.sp,
                        weight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    StarsRow(
                        starCount = 4.0, size = 24.dp
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    Row {
                        Text(
                            text = "₹14.99", color = Color.Green, fontSize = 24.sp
                        )
                        Text(
                            text = "/KG", fontSize = 24.sp
                        )
                    }
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                    HeadingText(
                        modifier = Modifier,
                        value = "Product Details",
                        size = 24.sp,
                        weight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    HeadingText(
                        modifier = Modifier,
                        value = "Orange is a vibrant and juicy citrus fruit, known for its refreshing flavor and bright color. With a tangy savory sweetness. it adds a burst of freshness to both sweet and savory dishes. The peel Of an orange is often used in cooking and baking to impart a zesty Read More",
                        size = 16.sp,
                        textAlign = TextAlign.Left,
                        weight = FontWeight.Normal,
                        color = Color.Black.copy(0.5f),
                        lineHeight = 20.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier, verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            HeadingText(
                                modifier = Modifier,
                                value = "Total Price",
                                size = 16.sp,
                                weight = FontWeight.Normal,
                                color = Color.Black.copy(0.5f)
                            )
                            Row {
                                Text(
                                    text = "₹14.99", color = Color.Green, fontSize = 24.sp
                                )
                                Text(
                                    text = "/KG", fontSize = 24.sp
                                )
                            }

                        }

                        PrimaryButton(
                            onClick = { /*TODO: Add to Cart Logic*/ },
                            shapes = RoundedCornerShape(50.dp)) {
                            Text(
                                text = "Add to Cart",
                                fontSize = 16.sp,
                                modifier = Modifier.width(150.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                    }


                }


            }

        }
    }


}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    DetailsScreen(
        navController = rememberNavController()
    )
}