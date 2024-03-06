package com.example.tresenraya.data.network

import android.util.Log
import com.example.tresenraya.data.network.model.GameData
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseService_6 @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "games"
    }

    fun createGame(gameData: GameData) {
        val gameReference = reference.child(PATH).push()
        gameReference.setValue(gameData)

        /*6.2-Añadimos un Log.i con un texto cualquiera para seguir la traza de ejecución y verificar
        que no hubo ningún error durante el camino*/
        Log.i("FirebaService",toString())
    }

}