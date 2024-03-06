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
class GameViewModel_12 @Inject constructor(private val firebaseService: FirebaseService_10): ViewModel(){

    private lateinit var userId: String

    private var _game = MutableStateFlow<GameModel?>(null)
    val game: StateFlow<GameModel?> = _game

    fun joinToGame(gameId: String, userId: String, owner: Boolean) {
        this.userId = userId
        if (owner) {
            join(gameId)
        } else {
            joinGameLikeGuest(gameId)
        }
    }

    /**********************************************************************************************/
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
            }
        }
    }

    private fun isMyTurn(): Boolean{
        return game.value?.playerTurn?.userId == userId
    }

    /*12.4-Creo la función onItemSelected a la cual se le pasa la posicion del tablero que ha sido
    seleccionada por un usuario */

    fun onItemSelected(position: Int) {

        val currentGame: GameModel = _game.value ?: return
        /*12.5-Obtenemos los datos del partido actual, pero antes de registrar un movimento del tablero
        tenemos que hacer comprobaciones:

            1ero-IsGameReady(Existe Player1 y Player2)
            2sng-Esa casilla del tablero está vacia
            3ero-IsMyTurn(Si es mi turno)
        */
        if(currentGame.isGameReady && currentGame.board[position] == PlayerType.Empty && isMyTurn()){

            /*12.5.1-Si todos estos se cumple actualizo en firebase el valor de esa casilla con mi X o 0,
            * como hago una opración de BD la lanzo dentro de una corrutina*/

            viewModelScope.launch {
                val newBoard = currentGame.board.toMutableList()
                newBoard[position] = getPlayer() ?: PlayerType.Empty //12.7.1 Completamos

                /*12.6-Pensando en firebase lo lógico sería obtener el game, hacer un copy y sobreescribir
                el valor, lo que pasa es que estamos en el viewModel, no podemos utilizar operaciones de
                firebase.

                Solución: Creo una variable newBoard,es decir, un nuevo tablero que va a ser una lista
                mutable. La cuestión es que necesito escribir una posición concreta del tablero y con el
                usuario concreto. Necesito crear un metodo que me devuelva el PlayerID para rellenar
                esa información ---> getPlayer()*/

                firebaseService.updateGame(

                    /*12.8-Momento de actualizar la BD, le pasamos esa copia de tablero que hemos realizado,
                    pero tendríamos  que pasarle tambien el playerTurn, ya que solo afectarían mis movimientos.

                    Entonces necesito saber quien es mi oponente,  por lo que creo un método getEnemyPlayer que
                    se encargue de recuperar el jugador enememigo*/

                    currentGame.copy(board = newBoard, playerTurn = getEnemyPlayer()!!).toData()

                    /*12.10-Con todos los cambios aplicados, ya deberíamos ser capaz de ejecutar y
                    jugar al 3 en raya.*/
                )
            }
        }
    }

    /*12.7-Creamos el método getPlayer que devuelve un PlayerType en función de si el userId de dicho
    juego coincide con el mio puedo determinar si soy el player 1 o player2*/
    private fun getPlayer(): PlayerType? {
        return when {
            (game.value?.player1?.userId == userId) -> PlayerType.FirstPlayer
            (game.value?.player2?.userId == userId) -> PlayerType.SecondPlayer
            else -> null
        }
    }

    /*12.9-Este método devuelve un PlayerModel, y cómo hacemos igual que arriba, comprobaríamos si el
    * userId del player 1 es igual al mio, entonces devuelvo el player 2 en caso contrario devuelvo
    * el player1 */
    private fun getEnemyPlayer(): PlayerModel? {
        return if (game.value?.player1?.userId == userId) game.value?.player2 else game.value?.player1
    }
}