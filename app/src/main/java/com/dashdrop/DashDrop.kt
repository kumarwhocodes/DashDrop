package com.dashdrop

import android.app.Application
import com.dashdrop.presentation.viewmodels.getCategoryList
import com.google.firebase.FirebaseApp

class DashDrop: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        getCategoryList()
    }
}