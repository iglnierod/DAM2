package com.example.navegacionventanas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.example.navegacionventanas.screens.MainNavigation
import com.example.navegacionventanas.ui.theme.NavegacionventanasTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacionventanasTheme {
                val navController = rememberNavController()
                MainNavigation(navController)
            }
        }
    }
}