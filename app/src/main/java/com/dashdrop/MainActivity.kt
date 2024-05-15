package com.dashdrop

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.dashdrop.fireStore.categoryList
import com.dashdrop.fireStore.getCategoryList
import com.dashdrop.navigation.Navigation
import com.dashdrop.presentation.viewmodels.HomeViewModel
import com.dashdrop.ui.theme.DashDropTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {

    private val vm by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            getCategoryList()
            setKeepOnScreenCondition {
                !vm.isReady.value
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
