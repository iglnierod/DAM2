package com.example.tresenraya.data.network.model

import java.util.Calendar


/*5.3-Creamos una clase GAMEDATA que va a tener los datos de una partida que guardaremos en firebase */
data class GameData_5 (
    val board:List<Int?>? = null,
    val gameId:String? = null,
    val player1:PlayerData? = null,
    val player2:PlayerData? = null,
    val playerTurn:PlayerData? = null
)

/*5.4-Creamos una clase PLAYERDATA que va a tener los datos de una jugador que guardaremos en firebase */
data class PlayerData_5(
    val userId:String? = Calendar.getInstance().timeInMillis.hashCode().toString(),
    val playerType:Int? = null
)