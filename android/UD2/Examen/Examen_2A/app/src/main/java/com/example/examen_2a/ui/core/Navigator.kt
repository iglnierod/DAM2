package com.example.examen_2a.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examen_2a.ui.home.ContactosScreen
import com.example.examen_2a.ui.nuevo.NuevoContacto

@Composable
fun Navegacion(navigationController: NavHostController) {
    NavHost(
        navController = navigationController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            ContactosScreen(navigationController)
        }
        composable(Routes.Nuevo.route) {
            NuevoContacto(navigationController = navigationController)
        }
    }
}

/**************************************************************************************************/
sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Nuevo : Routes("nuevo")
}