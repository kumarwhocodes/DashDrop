package com.dashdrop.presentation.viewmodels

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.sharp.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.dashdrop.R
import com.dashdrop.navigation.DashDropAppRouter
import com.dashdrop.navigation.Screen

sealed class BottomNavOptions(
    val route: Screen,
    @StringRes val labelOfIcon: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val onOptionClicked: (DashDropAppRouter) -> Unit
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
            it.navigateTo(Screen.HomeScreen)
        }
    )

    object FavouriteOption : BottomNavOptions(
        route = Screen.FavouriteScreen,
        labelOfIcon = R.string.Favourite,
        unselectedIcon = Icons.Outlined.Favorite,
        selectedIcon = Icons.Filled.Favorite,
        onOptionClicked = {
            it.navigateTo(Screen.FavouriteScreen)
        }
    )

    object CartOption : BottomNavOptions(
        route = Screen.CartScreen,
        labelOfIcon = R.string.Cart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        onOptionClicked = {
            it.navigateTo(Screen.CartScreen)
        }
    )

    object ProfileOption : BottomNavOptions(
        route = Screen.ProfileScreen,
        labelOfIcon = R.string.Profile,
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        onOptionClicked = {
            it.navigateTo(Screen.ProfileScreen)
        }
    )
}