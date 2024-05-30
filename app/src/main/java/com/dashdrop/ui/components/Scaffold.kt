package com.dashdrop.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dashdrop.navigation.Screen
import com.dashdrop.ui.theme.PrimaryColor
import com.dashdrop.ui.theme.TertiaryColor
import com.dashdrop.ui.theme.bg
import com.dashdrop.ui.theme.rubikRegularStyle
import com.dashdrop.ui.theme.rubikSemiBoldStyle
import com.dashdrop.ui.theme.scaffoldIconBackgroundColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTop(
    toolbarTitle: String,
    logOutButtonClicked: () -> Unit,
    navigationIconClicked: () -> Unit,
    containerColor: Color = PrimaryColor,
    contentColor: Color = Color.White,
    iconColor: Color = Color.White,
    iconBackgroundColor: Color = scaffoldIconBackgroundColor
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
                textAlign = TextAlign.Center,
                fontFamily = rubikSemiBoldStyle
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navigationIconClicked.invoke()
                },
                colors = IconButtonColors(
                    contentColor = iconColor,
                    containerColor = iconBackgroundColor,
                    disabledContentColor = iconColor,
                    disabledContainerColor = iconBackgroundColor
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Menu",
                    tint = iconColor
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    logOutButtonClicked.invoke()
                },
                colors = IconButtonColors(
                    contentColor = iconColor,
                    containerColor = iconBackgroundColor,
                    disabledContentColor = iconColor,
                    disabledContainerColor = iconBackgroundColor
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "LogOut",
                    tint = iconColor
                )
            }
        }
    )
}

@Composable
fun BottomNavBar(
    navController: NavController,
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier,
        containerColor = Color.White
    ) {

        NavigationBarItem(
            selected = currentRoute == Screen.HomeScreen.route,
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            icon = {
                Icon(
                    imageVector = if (currentRoute == Screen.HomeScreen.route) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home",
                    tint = if (currentRoute == Screen.HomeScreen.route) PrimaryColor else Color.Gray
                )
            },
            label = {
                Text(
                    text = if (currentRoute == Screen.HomeScreen.route) "Home" else "",
                    color = if (currentRoute == Screen.HomeScreen.route) PrimaryColor else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                unselectedTextColor = Color.Transparent,
                indicatorColor = PrimaryColor.copy(0.25f)
            )
        )

        NavigationBarItem(
            selected = currentRoute == Screen.FavouriteScreen.route,
            onClick = { navController.navigate(Screen.FavouriteScreen.route) },
            icon = {
                Icon(
                    imageVector = if (currentRoute == Screen.FavouriteScreen.route) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = if (currentRoute == Screen.FavouriteScreen.route) PrimaryColor else Color.Gray
                )
            },
            label = {
                Text(
                    text = if (currentRoute == Screen.FavouriteScreen.route) "Favourite" else "",
                    color = if (currentRoute == Screen.FavouriteScreen.route) PrimaryColor else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                unselectedTextColor = Color.Transparent,
                indicatorColor = PrimaryColor.copy(0.25f)
            )
        )

        NavigationBarItem(
            selected = currentRoute == Screen.CartScreen.route,
            onClick = { navController.navigate(Screen.CartScreen.route) },
            icon = {
                Icon(
                    imageVector = if (currentRoute == Screen.CartScreen.route) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart",
                    tint = if (currentRoute == Screen.CartScreen.route) PrimaryColor else Color.Gray
                )
            },
            label = {
                Text(
                    text = if (currentRoute == Screen.CartScreen.route) "Cart" else "",
                    color = if (currentRoute == Screen.CartScreen.route) PrimaryColor else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                unselectedTextColor = Color.Transparent,
                indicatorColor = PrimaryColor.copy(0.25f)
            )
        )

        NavigationBarItem(
            selected = currentRoute == Screen.ProfileScreen.route,
            onClick = { navController.navigate(Screen.ProfileScreen.route) },
            icon = {
                Icon(
                    imageVector = if (currentRoute == Screen.ProfileScreen.route) Icons.Filled.Person else Icons.Outlined.Person,
                    contentDescription = "Profile",
                    tint = if (currentRoute == Screen.ProfileScreen.route) PrimaryColor else Color.Gray
                )
            },
            label = {
                Text(
                    text = if (currentRoute == Screen.ProfileScreen.route) "Profile" else "",
                    color = if (currentRoute == Screen.ProfileScreen.route) PrimaryColor else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                unselectedTextColor = Color.Transparent,
                indicatorColor = PrimaryColor.copy(0.25f)
            )
        )
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            IconButton(
//                onClick = { navController.navigate(Screen.HomeScreen.route) },
//                colors = IconButtonColors(
//                    contentColor = PrimaryColor,
//                    containerColor = Color.White,
//                    disabledContentColor = Color.White,
//                    disabledContainerColor = Color.White
//                )
//            ) {
//                Icon(
//                    imageVector = if (currentRoute == Screen.HomeScreen.route) Icons.Filled.Home else Icons.Outlined.Home,
//                    contentDescription = "Home",
//                )
//            }
//            IconButton(
//                onClick = { navController.navigate(Screen.FavouriteScreen.route) },
//                colors = IconButtonColors(
//                    contentColor = PrimaryColor,
//                    containerColor = Color.White,
//                    disabledContentColor = Color.White,
//                    disabledContainerColor = Color.White
//                )
//            ) {
//                Icon(
//                    imageVector = if (currentRoute == Screen.FavouriteScreen.route) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
//                    contentDescription = "Favourite"
//                )
//            }
//            IconButton(
//                onClick = {
//                    navController.navigate(Screen.CartScreen.route)
//                    },
//                colors = IconButtonColors(
//                    contentColor = PrimaryColor,
//                    containerColor = Color.White,
//                    disabledContentColor = Color.White,
//                    disabledContainerColor = Color.White
//                )
//            ) {
//                Icon(
//                    imageVector = if (currentRoute == Screen.CartScreen.route) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
//                    contentDescription = "Cart"
//                )
//            }
//            IconButton(
//                onClick = { navController.navigate(Screen.ProfileScreen.route) },
//                colors = IconButtonColors(
//                    contentColor = PrimaryColor,
//                    containerColor = Color.White,
//                    disabledContentColor = Color.Black,
//                    disabledContainerColor = Color.White
//                )
//            ) {
//                Icon(
//                    imageVector =  if (currentRoute == Screen.ProfileScreen.route) Icons.Filled.Person else Icons.Outlined.Person,
//                    contentDescription = "Profile"
//                )
//            }
//        }
    }
}

@Composable
fun AddToCartBottomBar(
    addToCartButtonClicked: () -> Unit,
    price: String = "₹"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier, verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            HeadingText(
                modifier = Modifier,
                value = "Total Price",
                size = 16.sp,
                color = Color.Black.copy(0.5f),
                font = rubikRegularStyle
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 2.dp)
            ) {
                Text(
                    text = price, color = PrimaryColor, fontSize = 24.sp
                )
                Text(
                    text = "/KG", fontSize = 24.sp, color = TertiaryColor
                )
            }

        }

        PrimaryButton(
            onClick = {
                addToCartButtonClicked()
            },
            shapes = RoundedCornerShape(50.dp)
        ) {
            Text(
                text = "Add to Cart",
                fontSize = 16.sp,
                modifier = Modifier.width(150.dp),
                textAlign = TextAlign.Center
            )
        }

    }

}

@Composable
fun CheckoutBottomBar(
    price: String = "₹",
    buttonAction: () -> Unit,
    buttonText: String
) {
    Log.d("price", price)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier, verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            HeadingText(
                modifier = Modifier,
                value = "Total Price",
                size = 16.sp,
                color = Color.Black.copy(0.5f),
                font = rubikRegularStyle
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 2.dp)
            ) {
                Text(
                    text = price, color = bg, fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        PrimaryButton(
            onClick = {
                buttonAction()
            },
            shapes = RoundedCornerShape(50.dp),
        ) {
            Text(
                text = buttonText,
                fontSize = 16.sp,
                modifier = Modifier.width(150.dp),
                textAlign = TextAlign.Center
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column {
        ScaffoldTop(toolbarTitle = "Home",
            logOutButtonClicked = {},
            navigationIconClicked = {}
        )
        Spacer(modifier = Modifier.height(100.dp))

        BottomNavBar(
            navController = NavController(context = LocalContext.current)
        )

        AddToCartBottomBar(addToCartButtonClicked = { }, price = "b")
        CheckoutBottomBar(
            price = "322",
            buttonAction = {},
            buttonText = "Checkout"
        )

    }


}