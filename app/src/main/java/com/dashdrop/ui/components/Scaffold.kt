package com.dashdrop.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dashdrop.presentation.viewmodels.BottomNavOptions
import com.dashdrop.presentation.viewmodels.BottomNavOptions.Companion.menuItems
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
    bottomMenu : List<BottomNavOptions>,
    navController: NavController = rememberNavController()
) {

    NavigationBar(
        modifier = Modifier,
        containerColor = Color.White
    ) {

        val backStackEntry = navController.currentBackStackEntryAsState()

        for (menuitem in bottomMenu){
            val selected = menuitem.route.route == (backStackEntry.value?.destination?.route )

            NavigationBarItem(
                selected = selected,
                onClick = {
                          menuitem.onOptionClicked(navController)
                },
                icon = {
                    val currentIcon = if (selected)
                        menuitem.selectedIcon
                    else
                        menuitem.unselectedIcon

                    Icon(
                        imageVector = currentIcon,
                        contentDescription = stringResource(id = menuitem.labelOfIcon)
                    )
                },
                label = {
                   if (selected){
                       Text(text = stringResource(id = menuitem.labelOfIcon))
                   }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = bg,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = bg,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color.White
                )
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
            bottomMenu = menuItems
        )

    }


}