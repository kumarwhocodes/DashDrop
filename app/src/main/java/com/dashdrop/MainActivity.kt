package com.dashdrop

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.dashdrop.fireStore.categoryList
import com.dashdrop.fireStore.getCategoryList
import com.dashdrop.navigation.Navigation
import com.dashdrop.ui.theme.DashDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            getCategoryList()
            setKeepOnScreenCondition {
                categoryList.size == 0
            }
        }
        setContent {
            DashDropTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}
