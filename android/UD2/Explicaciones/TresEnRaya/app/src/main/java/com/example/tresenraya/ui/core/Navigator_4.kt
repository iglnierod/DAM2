package com.example.tresenraya.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tresenraya.ui.game.GameScreen
import com.example.tresenraya.ui.home.HomeScreen_4

/*4.1 Creamos un elemento composable que será el encargado de generar toda la navegación, en función
* de si estamos en el layout HOME o GAME*/

@Composable
fun ContentWrapper_4(navigationController: NavHostController) {

    /*4.2-Para navegar vamos necesitamos utilizar la función NavHost con su correspondiente
    navigationController y un punto de partida inicial.*/

    NavHost(
        navController = navigationController,
        startDestination = Routes.Home.route
    ) {

        /*4.3 Creamos un composable para la HOME y GAME*/
        composable(Routes.Home.route) {

            /*4.9-Modificamos nuestras llamadas a los composables del HomeScreen.kt y GameScreen.kt, ya que,
            * ahora tenemos que pasarle un parámetro (navController) para que refresque el valor según la
            * screen en la que se encuentre.
            *
            * No es buena práctica pasar el navigationController directamente y por eso lo encapsulo
            * en una función Lambda.
            * */

            /*4.11- Le pasamos al navidateToGame el navigationController*/
            HomeScreen_4(navigateToGame = {navigationController.navigate(Routes_4.Game.route)})
        }
        composable(Routes.Game.route)
        {
            GameScreen()
        }
    }
}

/*4.4 Creamos una clase Routes en la cual vamos a definir todas las rutas de navegación que va a tener
 nuestra APP y va a ser la única fuente de verdad (Buenas práctica).*/

sealed class Routes_4(val route: String) {
    object Home : Routes("home")
    object Game : Routes("game")
}

/*4.5-Modificamos el MainActivity pasandole este ContentWrapper(), ya que, va a ser el que generará
* la UI inicial y nos permitirá navegar a una UI-HOME o UI-GAME*/