package com.example.tresenraya.data.network.model

import com.example.tresenraya.ui.model.GameModel
import com.example.tresenraya.ui.model.PlayerModel
import com.example.tresenraya.ui.model.PlayerType
import java.util.Calendar

data class GameData_9 (

    val board:List<Int?>? = null,
    val gameId:String? = null,
    val player1:PlayerData? = null,
    val player2:PlayerData? = null,
    val playerTurn:PlayerData? = null
){
    /*9.8-Partiendo del data class GameData, necesitamos crear una función que nos parsee esos datos
    * a la capa del UI(GameModel) por eso le llamamos "toModel" */

    fun toModel(): GameModel {
        return GameModel(
            board = board?.map { PlayerType.getPlayerById(it) } ?: mutableListOf(),
            /*9.8.1-Obtenemos de cada uno de los jugadores, para que vaya saltando de uno a otro...
            en el caso de que no lo tengamos lo inicializamos con un mutableListof de ceros*/
            gameId = gameId.orEmpty(),

            /*9.8.2-Necesito que el tipo PlayerData tambien se parsee al GameModel por lo que necesito
            * crear el toModel() para la PlayerData*/

            player1 = player1!!.toModel(),
            player2 = player2?.toModel(),
            playerTurn = playerTurn!!.toModel()
        )
    }
}
/**************************************************************************************************/
data class PlayerData_9(
    val userId:String? = Calendar.getInstance().timeInMillis.hashCode().toString(),
    val playerType:Int? = null
){
    /*9.8.3-Creo la función que me devuelve en un PlayerModel los datos de tipo PlayerType*/
    fun toModel(): PlayerModel {
        return PlayerModel(userId!!, PlayerType.getPlayerById(playerType))
    }
}
