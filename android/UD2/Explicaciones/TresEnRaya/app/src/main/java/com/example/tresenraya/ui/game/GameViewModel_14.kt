package com.example.tresenraya.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tresenraya.data.network.FirebaseService_10
import com.example.tresenraya.ui.model.GameModel
import com.example.tresenraya.ui.model.PlayerModel
import com.example.tresenraya.ui.model.PlayerType
import com.example.tresenraya.ui.model.PlayerType_10
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel_14 @Inject constructor(private val firebaseService: FirebaseService_10): ViewModel(){

    private lateinit var userId: String

    private var _game = MutableStateFlow<GameModel?>(null)
    val game: StateFlow<GameModel?> = _game

    private var _winner = MutableStateFlow<PlayerType?>(null)
    val winner: StateFlow<PlayerType?> = _winner

    fun joinToGame(gameId: String, userId: String, owner: Boolean) {
        this.userId = userId
        if (owner) {
            join(gameId)
        } else {
            joinGameLikeGuest(gameId)
        }
    }

    private fun joinGameLikeGuest(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).take(1).collect {
                var result: GameModel? = it
                if (result != null) {
                    result = result.copy(player2 = PlayerModel(userId, PlayerType_10.SecondPlayer))
                    firebaseService.updateGame(result.toData())
                }
            }
            join(gameId)
        }
    }

    private fun join(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).collect {

                /*14.1- Aquí lo que ocurre es que estoy accediendo a firebase por el gameID, y claro
                * volco el resultado en la variable result y luego un segundo despues sobreescribo el
                * valor
                *
                *
                * Solución?¿....pasa por haacer una modificación en el isMyTurn pasándole el playerTurn
                * que se encuantra en el PlayerModel*/

                val result: GameModel? = it?.copy(isGameReady = it.player2 != null, isMyTurn = isMyTurn(it.playerTurn))
                _game.value = result

                verifyWinner()
            }
        }
    }


    private fun isMyTurn(playerTurn: PlayerModel): Boolean{ //14.1.1- Añado el playertURN en la cabecera

        /*14.2- Modificamos lo que tenemos

        * return game.value?.playerTurn?.userId == userId
        *
        * Por
        *
        * return playerTurn.userId == userId

        Así solo sería mi turno cuando el id de usuario del turno coincida con el mio...

        Actualizariamos arriba la llamada isMyTurn(it.playerTurn)
        * */
        return playerTurn.userId == userId

    }

    fun onItemSelected(position: Int) {

        val currentGame: GameModel = _game.value ?: return
        /*14.3 Aquí también tengo que corregir la llamada quedando isMyTurn(currentGame.playerTurn)*/
        if(currentGame.isGameReady && currentGame.board[position] == PlayerType.Empty && isMyTurn(currentGame.playerTurn)){
            viewModelScope.launch {
                val newBoard = currentGame.board.toMutableList()
                newBoard[position] = getPlayer() ?: PlayerType.Empty

                firebaseService.updateGame(
                    currentGame.copy(board = newBoard, playerTurn = getEnemyPlayer()!!).toData()
                )
            }
        }
    }

    private fun getPlayer(): PlayerType? {
        return when {
            (game.value?.player1?.userId == userId) -> PlayerType.FirstPlayer
            (game.value?.player2?.userId == userId) -> PlayerType.SecondPlayer
            else -> null
        }
    }

    private fun getEnemyPlayer(): PlayerModel? {//el PlayerModel puede ser nulo
        return if (game.value?.player1?.userId == userId) game.value?.player2 else game.value?.player1
    }

    private fun isGameWon(board: List<PlayerType>, playerType: PlayerType): Boolean {
        return when {
            //Row
            (board[0] == playerType && board[1] == playerType && board[2] == playerType) -> true
            (board[3] == playerType && board[4] == playerType && board[5] == playerType) -> true
            (board[6] == playerType && board[8] == playerType && board[8] == playerType) -> true

            //Column
            (board[0] == playerType && board[3] == playerType && board[6] == playerType) -> true
            (board[1] == playerType && board[4] == playerType && board[7] == playerType) -> true
            (board[2] == playerType && board[5] == playerType && board[8] == playerType) -> true

            //Diagonal
            (board[0] == playerType && board[4] == playerType && board[8] == playerType) -> true
            (board[2] == playerType && board[4] == playerType && board[6] == playerType) -> true

            else -> false
        }
    }

    private fun verifyWinner() {
        val board = _game.value?.board
        if (board != null && board.size == 9){
            when{
                isGameWon(board, PlayerType.FirstPlayer) -> { _winner.value = PlayerType.FirstPlayer} //13.3
                isGameWon(board, PlayerType.SecondPlayer) -> { _winner.value = PlayerType.SecondPlayer } //13.3
            }
        }
    }
}