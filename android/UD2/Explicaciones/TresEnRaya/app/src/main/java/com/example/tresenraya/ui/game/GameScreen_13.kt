package com.example.tresenraya.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import com.example.tresenraya.ui.theme.Background



@Composable
fun GameScreen_13(
    gameViewModel: GameViewModel_13 = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
) {

    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }
    val game: GameModel? by gameViewModel.game.collectAsState()

    /*13.6- Creamos una variable para almacenar el ganador y en función de ella creamos un composable
    * que pinte un box vacio cuando existe un winner, sino pinta el tablero*/

    val winner: PlayerType? by gameViewModel.winner.collectAsState()
    if (winner != null) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Background), contentAlignment = Alignment.Center
        ) {

            val currentWinner = if (winner == PlayerType.FirstPlayer) {
                "Player 1"
            } else {
                "Player 2"
            }
            Text(text = "Ha ganado el jugador: $currentWinner")
        }
    } else {
        Board_13(game, onItemSelected = { position -> gameViewModel.onItemSelected(position) })
    }
}

@Composable
fun Board_13(game: GameModel?,onItemSelected:(Int)-> Unit) {
    if (game == null) return
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

        Row {
            GameItem_13(game.board[0]) { onItemSelected(0) }
            GameItem_13(game.board[1]) { onItemSelected(1) }
            GameItem_13(game.board[2]) { onItemSelected(2) }
        }
        Row {
            GameItem_13(game.board[3]) { onItemSelected(3) }
            GameItem_13(game.board[4]) { onItemSelected(4) }
            GameItem_13(game.board[5]) { onItemSelected(5) }
        }
        Row {
            GameItem_13(game.board[6]) { onItemSelected(6) }
            GameItem_13(game.board[7]) { onItemSelected(7) }
            GameItem_13(game.board[8]) { onItemSelected(8) }
        }
    }
}

@Composable
fun GameItem_13(playerType: PlayerType, onItemSelected:()-> Unit) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, Accent))
            .clickable { onItemSelected },//12.3.1 lo ponemos clickable
        contentAlignment = Alignment.Center
    ) {
        Text(text = playerType.symbol)
    }
}
