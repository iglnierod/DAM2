package com.example.examen_2a

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ContactosApp: Application() {
    override fun onCreate(){
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}