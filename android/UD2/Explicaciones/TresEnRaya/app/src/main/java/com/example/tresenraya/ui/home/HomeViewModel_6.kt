package com.example.tresenraya.ui.home

import androidx.lifecycle.ViewModel
import com.example.tresenraya.data.network.FirebaseService
import com.example.tresenraya.data.network.model.GameData
import com.example.tresenraya.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class HomeViewModel_6 @Inject constructor(private val firebaseService: FirebaseService) : ViewModel() {

    fun onCreateGame_6() {
        firebaseService.createGame(createNewGame_6())
    }

    /*6.1-Creamos un método para registrar el primer juego, el cual devolverá un tipo de datos
    de tipo GameData que es la forma en la que se va a almacenar los datos por en la BD.*/

    private fun createNewGame_6(): GameData {
        val currentPlayer = PlayerData(playerType = 1)
        return GameData(
            board = List(9) { 0 },
            player1 = currentPlayer,
            playerTurn = currentPlayer,
            player2 = null
        )
    }
    fun onJoinGame_6(id: String) {

    }

}