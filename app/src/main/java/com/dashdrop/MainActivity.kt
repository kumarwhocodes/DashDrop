package com.dashdrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dashdrop.navigation.DashDropApp
import com.dashdrop.ui.theme.DashDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DashDropTheme {
                DashDropApp()
            }
        }
    }
}
