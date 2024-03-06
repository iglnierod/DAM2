package com.example.tresenraya.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tresenraya.ui.game.GameScreen
import com.example.tresenraya.ui.home.HomeScreen
import com.example.tresenraya.ui.home.HomeScreen_4
import com.example.tresenraya.ui.home.HomeScreen_8


@Composable
fun ContentWrapper_16(navigationController: NavHostController) {

    NavHost(
        navController = navigationController,
        startDestination = Routes.Home.route
    ) {

        composable(Routes.Home.route) {


            HomeScreen_8(navigateToGame = {gameId: String, userId: String,owner: Boolean ->
                navigationController.navigate(Routes_8.Game.createRoute(gameId, userId, owner))
            })
        }

        composable(
            Routes.Game.route,
            arguments = listOf(
                navArgument("gameId") { type = NavType.StringType },
                navArgument("userId") { type = NavType.StringType },
                navArgument("owner") { type = NavType.BoolType }
            )
        )
        {
            GameScreen(
                gameId = it.arguments?.getString("gameId").orEmpty(),
                userId = it.arguments?.getString("userId").orEmpty(),
                owner = it.arguments?.getBoolean("owner") ?: false,
                navigateToHome = { navigationController.popBackStack() } //AÑADIMOS LA VUELTA AL HOME
            )
        }
    }
}


sealed class Routes_16(val route: String) {
    object Home : Routes("home")
    object Game : Routes("game/{gameId}/{userId}/{owner}") {
        fun createRoute(gameId: String, userId: String, owner: Boolean): String {
            return "game/$gameId/$userId/$owner"
        }
    }
}

