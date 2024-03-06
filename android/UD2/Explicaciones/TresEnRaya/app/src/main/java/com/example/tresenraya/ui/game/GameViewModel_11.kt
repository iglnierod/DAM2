package com.example.tresenraya.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tresenraya.data.network.FirebaseService_10
import com.example.tresenraya.ui.model.GameModel
import com.example.tresenraya.ui.model.PlayerModel
import com.example.tresenraya.ui.model.PlayerType_10
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel_11 @Inject constructor(private val firebaseService: FirebaseService_10): ViewModel(){

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

    /*11.3-Antes de hacer ningún cambio reestructuro el código del método joinGameLikeOwner y  joinGameLikeGuest
    ya que tienen una parte común. Para unificarlo creo una nueva función llamada Join.
    *
    * Ahora necesito el valor de isMyTurn por lo tanto a la derecha del isGameReady voy hacer una llamada
     a un método isMyTurn() en el cual vamos a delegar el trabajo de obtener el turno*/

    private fun join(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).collect {
                val result: GameModel? = it?.copy(isGameReady = it.player2 != null, isMyTurn = isMyTurn())
                _game.value = result
            }
        }
    }

    /*11.4-Creamos un método privado que calcula si es nuestro turno */
    private fun isMyTurn(): Boolean{
        return game.value?.playerTurn?.userId == userId
        //Obtenemos al game.value y sino es nulo devolvemos el playerturn del userid que acabo de buscar
    }



}