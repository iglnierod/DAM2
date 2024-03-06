package com.example.tresenraya.data.network

import com.example.tresenraya.data.network.model.GameData
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseService_5 @Inject constructor(private val reference: DatabaseReference) {

    /*5.1-Creamos la clase FirebaseService utilizando inyección de dependencias, pasando el databaseReference,
    aunque no lo tengamos implementado.

    Crearemos un PATH o directorio donde van a estar todos las partidas("games") donde guardar esta info
    utilizando un companion object{}*/

    companion object {
        private const val PATH = "games"
    }

    /*5.2-Creamos la función createGame() que va a ser la encargada de guardar datos en BD.
    Aún no tengo claro el modelo de datos que voy a guardar por lo tanto crearemos un nuevo directorio
    "MODEL" donde definiremos cómo es el tipo de datos que queremos guardar el cual llamaremos GameData.kt */


    fun createGame(gameData: GameData) {

        /*5.5-Una vez definido el tipo GameData es hora de guardar en la BD, para ello, necesitamos
        acceder a la última posición de la BD, hacer un push para crear un hueco donde almacenar y
        setear el valor.*/
        val gameReference = reference.child(PATH).push()
        gameReference.setValue(gameData)

    }

}