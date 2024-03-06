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
class GameViewModel_10 @Inject constructor(private val firebaseService: FirebaseService_10): ViewModel(){

    /*10.1 -Creo una variable _game que va a ser una mutableStateFlow de GameModel que va almacenar
    los datos de juego en el que estamos, crearemos también la variable pública game que va a ser lla
    que le pase los datos al GameScreen()*/

    private lateinit var userId: String

    private var _game = MutableStateFlow<GameModel?>(null)

    val game: StateFlow<GameModel?> = _game

    fun joinToGame(gameId: String, userId: String, owner: Boolean) {
        this.userId = userId
        if (owner) {
            joinGameLikeOwner(gameId)
        } else {
            joinGameLikeGuest(gameId)
        }
    }

    /**********************************************************************************************/

    private fun joinGameLikeOwner(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).collect {

                /*10.2-Ahora cómo tenemos la variable game vamos a llamar a firebase y vamos recuperando
                los datos y los seteamos en la variable, vamos recuperando el juego. Con los valores que
                tengo puedo obtener los datos de firebase, si el player2= NULL, claramente soy el player1
                el owner*/

                /*10.6.1-  Ahora tenenos que comprobar si ya hai  un jugador dos...es decir a la hora
                de explotar los datos tengo que comprobar aquí si soy owner o guest, entonces aquí tengo
                * el it, y puedo preguntarle entonces.. modificamos y queda
                */

                val result: GameModel? = it?.copy(isGameReady = it.player2 != null)
                _game.value = result
            }
        }
    }

    /*10.9-Creamos el PLAYER2, la lógica es similar a la del OWNER:

    La linea de  ejecución del player1 sería: creo la partida y automáticamente soy el judador 1

    La linea de ejecución del player2 sería: me uno primero a la partida, por tanto, tengo que pasarle el id,
    luego obtengo los datos de la partida y compienzo el juego. En otras palabras el player2 no es null...
    tenemos que ponerle nuestro usuario */

    private fun joinGameLikeGuest(gameId: String) {
        viewModelScope.launch {

            /*10.9.1-Yo arriba en la función joinToGame en función de si soy el player1(owner) o el guest
            llamo a uno u otro método.

            Cogemos esto del owner y modificamos usando el take(1) que se va a encargar de coger el
            game y guardarlo en la variable result, y luego con la función copy hacemos la copia del
            resultado y lo volvemos a igualar a result para que actualice el dato y entremedias
            le decimos que el campo player2 va a ser igual al tipo Player2

            Falta crear método update en el FirebaseService
            */



            /*10.9.5-Modifico lo que hicimos anteriormente, y en este caso tengo que tener cuidado de que
            el result no sea nulo, sino no va a poder actualizarse*/
            firebaseService.joinToGame(gameId).take(1).collect {
                var result: GameModel? = it
                if (result != null) {
                    result = result.copy(player2 = PlayerModel(userId, PlayerType_10.SecondPlayer))
                    firebaseService.updateGame(result.toData())
                }
            }

            firebaseService.joinToGame(gameId).collect {
                val result: GameModel? = it?.copy(isGameReady = it.player2 != null)
                _game.value = result
            }

            /*10.9.6-La primera vez que entre como invitado me suscriba, obtenga el modelo de datos
            lo modifico y luego estoy coleteandolo de forma normal*/
        }
    }

}