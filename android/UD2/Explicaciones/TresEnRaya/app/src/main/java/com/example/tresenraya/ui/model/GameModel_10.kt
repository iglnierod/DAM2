package com.example.tresenraya.ui.model

import com.example.tresenraya.data.network.model.GameData
import com.example.tresenraya.data.network.model.PlayerData


data class GameModel_10(
    val board: List<PlayerType>,
    val player1: PlayerModel,
    val player2: PlayerModel?,
    val playerTurn: PlayerModel,
    val gameId: String,
    val isGameReady: Boolean = false

    /*10.5-Metemos aquí el isGameReady inicializado a false en el momento de creación del juego*/
){
    /*10.9.3-Por lo tanto de la dataClass vamos a crear una función toData que será un mapper que se
    encargue de pasarle los datos del GameModel al GameData */
    fun toData(): GameData {
        return GameData(
            board = board.map { it.id }, //Devuelvo las posiciones
            gameId = gameId,
            player1 = player1.toData(),
            player2 = player2?.toData(),
            playerTurn = playerTurn.toData()
            //isGameReady este no lo espera porque solo es util para la UI, no tiene que llegar a firebase
        )
    }
}

/*10.9.4-Ahora creamos la clase de datos playerModel, una vez creado debemos modificar la llamada y
en el gameViewModel que es donde empezamos utilizando el UpdateGame */

data class PlayerModel_10(val userId: String, val playerType: PlayerType) {
    fun toData(): PlayerData {
        return PlayerData(
            userId = userId,
            playerType = playerType.id
        )
    }
}


sealed class PlayerType_10(val id: Int, val symbol: String) {
    object FirstPlayer : PlayerType(2, "X")
    object SecondPlayer : PlayerType(3, "O")
    object Empty : PlayerType(0, "")

    companion object {
        fun getPlayerById(id: Int?): PlayerType {
            return when (id) {
                FirstPlayer.id -> FirstPlayer
                SecondPlayer.id -> SecondPlayer
                else -> Empty
            }
        }
    }
}