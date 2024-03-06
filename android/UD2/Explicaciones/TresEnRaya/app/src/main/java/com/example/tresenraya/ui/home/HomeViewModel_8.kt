package com.example.tresenraya.ui.home

import androidx.lifecycle.ViewModel
import com.example.tresenraya.data.network.FirebaseService
import com.example.tresenraya.data.network.model.GameData
import com.example.tresenraya.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class HomeViewModel_8 @Inject constructor(private val firebaseService: FirebaseService) : ViewModel() {

    fun onCreateGame_8(navigateToGame: (String, String, Boolean) -> Unit) {

        /*8.1-Para navegar de HOME a GAME necesitamos saber si somos el player1 o player2, el gameId
        y el estado del juego. Estos datos me los va a proveer desde la HomeScreen los paso en una lambda.*/

        val game: GameData = createNewGame_8()
        val gameId: String = firebaseService.createGame(game)
        val userId: String = game.player1?.userId.orEmpty()
        val owner = true

        /*8.3-Modificamos la cabecera con los parámetros nuevos y pasamos a la lambda los valores
        recuperados*/
        navigateToGame(gameId, userId, owner)
    }

    /*8.4-Completamos el onJoinGame, para unirnos a una partida necesitamos el idPartida y adicionalmente
     los mismos datos que se le pasaron el en onCreate, por lo que, reutilizamos lo que tenemos*/
    fun onJoinGame_8(gameId: String,navigateToGame: (String, String, Boolean) -> Unit) {
        val owner = false
        navigateToGame(gameId, createUserId(), owner)//
    }

    /*8.5-Ccreamos la función createUserId() para que al player2 que nunca se unió le de un ID con el
    que registrarse*/
    private fun createUserId(): String{
        return Calendar.getInstance().timeInMillis.hashCode().toString()
    }

    private fun createNewGame_8(): GameData {
        val currentPlayer = PlayerData(playerType = 1)
        return GameData(
            board = List(9) { 0 },
            player1 = currentPlayer,
            playerTurn = currentPlayer,
            player2 = null
        )
    }



}