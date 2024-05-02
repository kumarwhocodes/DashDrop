package com.dashdrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dashdrop.postOfficeApp.PostOfficeApp
import com.dashdrop.ui.theme.DashDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashDropTheme {
                PostOfficeApp()
            }
        }
    }
}
