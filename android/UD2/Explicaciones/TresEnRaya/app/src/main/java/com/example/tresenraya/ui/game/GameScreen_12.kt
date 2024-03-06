package com.example.tresenraya.ui.game


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tresenraya.ui.model.GameModel
import com.example.tresenraya.ui.model.PlayerType
import com.example.tresenraya.ui.theme.Accent


@Composable
fun GameScreen_12(
    gameViewModel: GameViewModel_12 = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
) {

    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }

    val game: GameModel? by gameViewModel.game.collectAsState()
    Board_12(game, onItemSelected= {position -> gameViewModel.onItemSelected(position)})//12.3.3 Le pasamos la lambda
}


@Composable
fun Board_12(game: GameModel?,onItemSelected:(Int)-> Unit) { //12.3.2 Le pasamos la lambda al board

    /*12.2.2-Antes de crear la lógica, siempre evaluamos si existe el game, para evitar que en el caso de
    que no exista me puedan colar un posible nulo, meto una comprabación con un if al principio y me
    olvido de complicaciones en el futuro*/

    if (game == null) return //12.2.3-El IF, y elimino las comprobaciones que realizaba
    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = game.gameId)

        val status: String = if (game.isGameReady) {
            if (game.isMyTurn) {
                "Tu turno"
            } else {
                "Turno rival"
            }
        } else {
            "Esperando por el jugador 2"
        }

        Text(text = status)

        /*12.3-Es momento de completar el tablero para ver si los cambios que hacemos están todos bien,
         cómo nuestros datos no dependen de firebase, no tendríamos problema al pasarle las posiciones
         del tablero de forma manual.

         La cuestión es que cuando yo pulse una de las casillas del tablero cómo sabe el viewModel que
         PLAYER ha marcado la casilla?¿. Por este motivo voy a pasar en la cabecera una función lambda
         que me solicite dicha informacion.*/

        Row {
            GameItem_12(game.board[0]) { onItemSelected(0) }  //12.4.1 Añado la función lambda así escrita,sino se ejecutaria automáticamente
            GameItem_12(game.board[1]) { onItemSelected(1) }
            GameItem_12(game.board[2]) { onItemSelected(2) }
        }
        Row {
            GameItem_12(game.board[3]) { onItemSelected(3) }
            GameItem_12(game.board[4]) { onItemSelected(4) }
            GameItem_12(game.board[5]) { onItemSelected(5) }
        }
        Row {
            GameItem_12(game.board[6]) { onItemSelected(6) }
            GameItem_12(game.board[7]) { onItemSelected(7) }
            GameItem_12(game.board[8]) { onItemSelected(8) }
        }
    }
}

@Composable
fun GameItem_12(playerType: PlayerType, onItemSelected:()-> Unit) { //12.3.1 Añado la función lambda
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, Accent))
            .clickable { onItemSelected },//12.3.1 lo ponemos clickable
        contentAlignment = Alignment.Center
    ) {
        /*12.1-En el tablero tenemos harcodeada la "Y" para jugar, y lo que queremos es que en función
        de si es player1 o player2 pase una ficha u otra. Si le pasarmos el playerType() al GameItem en
        la cabecera cómo parámetro podemos lograr que vaya cambiando la ficha.*/

        Text(text = playerType.symbol)

        /*12.2.1-Si se ejecuta el tablero sale vacio porque aún no hemos creado la lógica*/
    }
}
