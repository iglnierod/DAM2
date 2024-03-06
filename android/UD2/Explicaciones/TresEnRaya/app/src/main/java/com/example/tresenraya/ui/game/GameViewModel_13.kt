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
class GameViewModel_13 @Inject constructor(private val firebaseService: FirebaseService_10): ViewModel(){

    private lateinit var userId: String

    private var _game = MutableStateFlow<GameModel?>(null)
    val game: StateFlow<GameModel?> = _game

    /*13.3-Creamos la variable y la inicializamos a null, en el momento que _winner no es null
    ya tendríamos un ganador*/
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
                val result: GameModel? = it?.copy(isGameReady = it.player2 != null, isMyTurn = isMyTurn())
                _game.value = result

                verifyWinner()
            }
        }
    }

    private fun isMyTurn(): Boolean{
        return game.value?.playerTurn?.userId == userId

    }


    fun onItemSelected(position: Int) {

        val currentGame: GameModel = _game.value ?: return

        if(currentGame.isGameReady && currentGame.board[position] == PlayerType.Empty && isMyTurn()){
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

    /*13.1-Necesitamos crear una función privada que reciba un tablero y verifique si ha ganado o no.
    Por lo tanto creamos la función isGameWon donde le paso un tablero, que no es más que una lista de
    playerType.

    Para determinar si alguine ha ganado el juego necesito ir comprobando si el PLayerID de la posicion 0
    es igual al de la posicion 1 y la posicion 2, ya tendría un ganador. Esto para la primera fila; sería lo mismo
    para el resto de filas, columnas y diagonales.

    Quien llama a este método, no lo puede llamar cualquiera....entonces es momento de crear
    el método verifyWinner*/

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

    /*13.2-Creo una función que pregunta al isGameWon en función del playerType que le pasa (player1/player2)
    si alguno de los dos ha ganado el juego. Inicialmente comprobamos que el tablero esté bien, no tenga
    10 posiciones etc...*/
    private fun verifyWinner() {
        val board = _game.value?.board
        if (board != null && board.size == 9){
            when{
                /*13.3-Necesito tener una variable donde guardar el ganador, creamos _winner*/
                isGameWon(board, PlayerType.FirstPlayer) -> { _winner.value = PlayerType.FirstPlayer}
                isGameWon(board, PlayerType.SecondPlayer) -> { _winner.value = PlayerType.SecondPlayer }

                /*13.4-Este función debería comprobarse después de cad movimiento, por lo que tendremos
                 que llamar al verifyWInner() en el join.*/
            }
        }
    }
}