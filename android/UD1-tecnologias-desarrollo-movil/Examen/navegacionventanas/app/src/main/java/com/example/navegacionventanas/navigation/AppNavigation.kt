package com.example.navegacionventanas.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navegacionventanas.screens.AboutScreen
import com.example.navegacionventanas.screens.ArticlesScreen
import com.example.navegacionventanas.screens.CardDialogScreen
import com.example.navegacionventanas.screens.DialogScreen
import com.example.navegacionventanas.screens.EjemploScreen
import com.example.navegacionventanas.screens.LoginScreen
import com.example.navegacionventanas.screens.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController: NavController, drawerState: DrawerState){

    NavHost(navController = navController as NavHostController, startDestination = AppScreens.Articles.route) {
        composable(AppScreens.Articles.route) {
            ArticlesScreen(drawerState)
        }
        composable(AppScreens.About.route) {
            AboutScreen(drawerState)
        }
        composable(AppScreens.Settings.route) {
            SettingsScreen(drawerState)
        }
        composable(AppScreens.Ejemplo.route) {
            EjemploScreen(drawerState)
        }
        composable(AppScreens.Login.route) {
            LoginScreen(drawerState)
        }
        composable(AppScreens.Dialog.route) {
            DialogScreen(drawerState)
        }
        composable(AppScreens.CardDialog.route) {
            CardDialogScreen(drawerState)
        }
    }
}
