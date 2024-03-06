package com.example.tresenraya.data.network

import com.example.tresenraya.data.network.model.GameData
import com.example.tresenraya.ui.model.GameModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseService_9 @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "games"
    }

    fun createGame(gameData: GameData): String {

        val gameReference = reference.child(PATH).push()
        val key = gameReference.key
        val newGame = gameData.copy(gameId = key)
        gameReference.setValue(gameData)
        return newGame.gameId.orEmpty()

    }

    /*9.4-Definimos la funcion joinGame a la cual le pasaremos el gameID de la partida a la que queremos
    unirnos. Necesitamos recuperar de firebase la partida especifica y guardarlos en algún sitio para
    pasarselos. Creo un nuevo directorio MODEL donde voy a definir unas clases de datos que voy a utilizar
    a nivel de UI, equivalente a lo que tenemos con GameData para Firebase.

    Luego tendremos que parsear los datos*/

    fun joinToGame(gameId:String): Flow<GameModel?> {
        return reference.database.reference.child("${FirebaseService.PATH}/$gameId").snapshots.map { dataSnapshot ->
            dataSnapshot.getValue(GameData::class.java)?.toModel()
        }
    }

}