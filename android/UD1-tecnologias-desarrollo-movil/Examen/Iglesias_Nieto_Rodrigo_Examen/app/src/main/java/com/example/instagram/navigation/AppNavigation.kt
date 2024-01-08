package com.example.instagram.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagram.screens.FeedScreen
import com.example.instagram.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    /*
    * Esta variable navControlle vamos a tener que pasarla entre las distintas ventanas para saber
    * en que punto estamos exactamente
    * */
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route ) {
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScreens.FeedScreen.route){
            FeedScreen(navController)
        }
    }
}