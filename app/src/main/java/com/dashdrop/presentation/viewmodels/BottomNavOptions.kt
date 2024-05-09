package com.dashdrop.presentation.viewmodels

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen

sealed class BottomNavOptions(
    val route: Screen,
    @StringRes val labelOfIcon: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val onOptionClicked: (NavController) -> Unit
) {

    companion object {
        val menuItems = listOf(
            HomeOption,
            FavouriteOption,
            CartOption,
            ProfileOption
        )
    }

    object HomeOption : BottomNavOptions(
        route = Screen.HomeScreen,
        labelOfIcon = R.string.Home,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        onOptionClicked = {
            it.navigate(Screen.HomeScreen.route)
        }
    )

    object FavouriteOption : BottomNavOptions(
        route = Screen.FavouriteScreen,
        labelOfIcon = R.string.Favourite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        onOptionClicked = {
            it.navigate(Screen.FavouriteScreen.route){

            }
        }
    )

    object CartOption : BottomNavOptions(
        route = Screen.CartScreen,
        labelOfIcon = R.string.Cart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        onOptionClicked = {
            it.navigate(Screen.CartScreen.route)
        }
    )

    object ProfileOption : BottomNavOptions(
        route = Screen.ProfileScreen,
        labelOfIcon = R.string.Profile,
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        onOptionClicked = {
            it.navigate(Screen.ProfileScreen.route)
        }
    )
}