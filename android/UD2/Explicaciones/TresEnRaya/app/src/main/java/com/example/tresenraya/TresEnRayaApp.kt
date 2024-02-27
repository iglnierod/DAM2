package com.example.tresenraya

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TresEnRayaApp: Application() {
    override fun onCreate(){
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}