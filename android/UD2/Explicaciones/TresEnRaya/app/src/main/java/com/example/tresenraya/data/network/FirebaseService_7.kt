package com.example.tresenraya.data.network

import com.example.tresenraya.data.network.model.GameData
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseService_7 @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "games"
    }

    /*7.1-Obtenemos del gameReference el key, problema esto se nutre del gameData. Utilizamos la instrucción
    copy y creamos un game nuevo, por lo tanto ya podemos pasar a la UI el GameID.*/

    fun createGame(gameData: GameData): String {
        val gameReference = reference.child(PATH).push()
        val key = gameReference.key
        val newGame = gameData.copy(gameId = key)
        gameReference.setValue(gameData)
        return newGame.gameId.orEmpty()
    }

}