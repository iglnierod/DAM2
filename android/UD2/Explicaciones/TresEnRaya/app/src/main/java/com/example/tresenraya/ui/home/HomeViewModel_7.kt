package com.example.tresenraya.ui.home

import androidx.lifecycle.ViewModel
import com.example.tresenraya.data.network.FirebaseService
import com.example.tresenraya.data.network.model.GameData
import com.example.tresenraya.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class HomeViewModel_7 @Inject constructor(private val firebaseService: FirebaseService) : ViewModel() {

    /*7.2-Guardo el gameId en una nueva variable, ya lo tendríamos listo*/
    fun onCreateGame_7() {
        val gameId: String = firebaseService.createGame(createNewGame_7())
    }

    private fun createNewGame_7(): GameData {
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