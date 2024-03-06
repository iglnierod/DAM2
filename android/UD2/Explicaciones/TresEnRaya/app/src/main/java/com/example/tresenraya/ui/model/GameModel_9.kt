package com.example.tresenraya.ui.model



/*9.5-Creamos el dataclass GameModel con la estructura similar al GameData pero con pequeñas variaciones*/
data class GameModel_9(
    val board: List<PlayerType>,
    val player1: PlayerModel_9,
    val player2: PlayerModel_9?,
    val playerTurn: PlayerModel,
    val gameId: String
)

/**************************************************************************************************/
/*9.6 Defino una data class específica para el tipo de jugador, igual que hicimos en el gameData*/
data class PlayerModel_9(val userId: String, val playerType: PlayerType)


/**************************************************************************************************/
/*9.7 Defino un id para cada tipo de player y el simbolo con el que participa para tenerlo tipificado
* y empezar a distinguirlos*/
sealed class PlayerType_9(val id: Int, val symbol: String) {
    object FirstPlayer : PlayerType(2, "X")
    object SecondPlayer : PlayerType(3, "O")
    object Empty : PlayerType(0, "")

    /*9.8.4-Creo la función que compruebe que tipo de jugador soy por el ID*/
    companion object {
        fun getPlayerById(id: Int?): PlayerType {
            return when (id) {
                PlayerType_10.FirstPlayer.id -> PlayerType_10.FirstPlayer
                PlayerType_10.SecondPlayer.id -> PlayerType_10.SecondPlayer
                else -> PlayerType_10.Empty
            }
        }
    }
}