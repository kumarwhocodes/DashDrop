package com.dashdrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.dashdrop.navigation.Navigation
import com.dashdrop.ui.theme.DashDropTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashDropTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}
