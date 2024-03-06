package com.example.tresenraya.data.network

import com.example.tresenraya.data.network.model.GameData
import com.example.tresenraya.ui.model.GameModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseService_10 @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "games"
    }

    /**********************************************************************************************/
    fun createGame(gameData: GameData): String {
        val gameReference = reference.child(PATH).push()
        val key = gameReference.key
        val newGame = gameData.copy(gameId = key)
        gameReference.setValue(gameData)
        return newGame.gameId.orEmpty()
    }
    /**********************************************************************************************/

    fun joinToGame(gameId:String): Flow<GameModel?> {
        return reference.database.reference.child("${FirebaseService.PATH}/$gameId").snapshots.map { dataSnapshot ->
            dataSnapshot.getValue(GameData::class.java)?.toModel()
        }
    }
    /**********************************************************************************************/

    /*10.9.2- Creamos el método update, nos llega un GameModel pero para insertar en firebase los DTO
    tienen que hablar en el lenguaje GameData, por lo quenecesitamos hacer un mapper pero al revés.*/
    fun updateGame(gameData: GameData){

        /*10.9.7-Comprobamos si gamedata.gameId es nulo, porque si es nulo no voy a poder modificarlo */

        if(gameData.gameId != null){
            reference.child(PATH).child(gameData.gameId).setValue(gameData)
        }
    }
}