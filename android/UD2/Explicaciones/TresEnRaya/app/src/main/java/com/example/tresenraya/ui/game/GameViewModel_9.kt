package com.example.tresenraya.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tresenraya.data.network.FirebaseService_9
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel_9 @Inject constructor(private val firebaseService: FirebaseService_9): ViewModel(){

    /*9.2-Creamos los métodos y separamos en función del rol (owner/guest) por lo que vamos a
    * generar dos funciones privadas*/

    lateinit var userId: String

    fun joinToGame(gameId: String, userId: String, owner: Boolean) {
        this.userId = userId
        if (owner) {
            joinGameLikeOwner(gameId)
        } else {
            joinGameLikeGuest(gameId)
        }
    }

    /**********************************************************************************************/

    /*9.3-Creamos el primer método, este va a tener una coroutina para lanzar y registrar el usuario
    como owner y pedir los datos a firebase. Llamamos al joinToGame que aún está sin definir y lo
    definimos en el FireService*/
    private fun joinGameLikeOwner(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).collect {

                //Probamos a ejecutar con el LogCat
                Log.i("Game like a owner", it.toString())
            }

        }
    }

    private fun joinGameLikeGuest(gameId: String) {
        //Pendiente de definir
    }
}