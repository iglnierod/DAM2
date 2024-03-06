package com.example.tresenraya.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tresenraya.ui.game.GameScreen_8
import com.example.tresenraya.ui.home.HomeScreen_8


@Composable
fun ContentWrapper_8(navigationController: NavHostController) {

    NavHost(
        navController = navigationController,
        startDestination = Routes.Home.route
    ) {

        composable(Routes.Home.route) {
            /*8.6-Acabamos de pulsar el botón "CREATE GAME", por lo tanto, para nuestro user ya tenemos
            una partida registrada en firebase a nuestro nombre, ahora necesitamos navegar a la pestaña
            de juego. Por lo tanto, nuestra ruta cambió tenemos que ir a un GameID concreto, por lo que
            necesitamos modificar las routas, pasandole los 3 parámetros.*/

            /*8.10-Con la ruta creada, ya puedo navegar a la partida específica */
            HomeScreen_8(navigateToGame = {gameId: String, userId: String,owner: Boolean ->
                navigationController.navigate(Routes_8.Game.createRoute(gameId, userId, owner))
            })
        }

        /*8.8-Ahora deberíamos modificar tambien para el Game, entonces teniendo la ruta ahora
        * deberiamos pasarle los parámetros para la navegación
        *
        * Por lo tanto, creamos una lista de argumentos de la siguiente forma indicandole el tipo de
        * datos que es cada uno de los argumentos.
        *
        * Una vez tengamos la ruta con sus argumentos correspondientes tendremos que cambiar la llamada
        * del GameScreen pasándole los valores correspondientes, aquí tenemos que indicarle a que es
        * equivalente cada uno de esos parámetros que le pasamos.
        *
        * Como utilizamos una lista de argumentos necesitamos un iterator para recorrerla*/
        composable(
            Routes_8.Game.route,
            arguments = listOf(
                navArgument("gameId") { type = NavType.StringType },
                navArgument("userId") { type = NavType.StringType },
                navArgument("owner") { type = NavType.BoolType }
            )
        )
        {
            GameScreen_8(
                gameId = it.arguments?.getString("gameId").orEmpty(),
                userId = it.arguments?.getString("userId").orEmpty(),
                owner = it.arguments?.getBoolean("owner") ?: false
            )
        }
    }
}

/**************************************************************************************************/
sealed class Routes_8(val route: String) {
    object Home : Routes("home")

    /*8.7-Defino una nueva función que con los valores que tenemos crea la nueva ruta, que no es más,
    que una concatenación de strings */

    object Game : Routes("game/{gameId}/{userId}/{owner}") {
        fun createRoute(gameId: String, userId: String, owner: Boolean): String {
            return "game/$gameId/$userId/$owner"
        }
    }
}

