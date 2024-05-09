package com.dashdrop.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.dashdrop.navigation.Screen
import com.dashdrop.ui.theme.bg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTop(
    toolbarTitle: String,
    logOutButtonClicked: () -> Unit,
    navigationIconClicked: () -> Unit,
    containerColor: Color = bg,
    contentColor: Color = Color.White,
    iconColor: Color = Color.White
) {

    TopAppBar(
        modifier = Modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor
        ),
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toolbarTitle,
                color = contentColor,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Menu",
                    tint = iconColor
                )
            }
        },
        actions = {
            IconButton(onClick = {
                logOutButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "LogOut",
                    tint = iconColor
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    navController: NavController
) {

    NavigationBar(
        modifier = Modifier,
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            IconButton(onClick = { navController.navigate(Screen.HomeScreen.route) },
                colors = IconButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.White
                    )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = { navController.navigate(Screen.FavouriteScreen.route) },
                colors = IconButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favourite"
                )
            }
            IconButton(onClick = { navController.navigate(Screen.CartScreen.route) },
                colors = IconButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart"
                )
            }
            IconButton(onClick = { navController.navigate(Screen.ProfileScreen.route) },
                colors = IconButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile"
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    Column {
//        ScaffoldTop(toolbarTitle = "Home",
//            logOutButtonClicked = {},
//            navigationIconClicked = {}
//        )
//        Spacer(modifier = Modifier.height(100.dp))
//
//        BottomNavBar(
//            bottomMenu = menuItems
//        )
//
//    }
//
//
//}