package com.example.tresenraya.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import com.example.tresenraya.ui.theme.Accent

@Composable
fun GameScreen_11(
    gameViewModel: GameViewModel_11 = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
) {

    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }

    val game: GameModel? by gameViewModel.game.collectAsState()
    Board_11(game)
}


@Composable
fun Board_11(game: GameModel?) {
    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = game?.gameId.orEmpty())

        /*11.1-Necesitamos gestionar los turnos, para ello realizamos algo similar o lo que hicimos
        con el IsGameReady, creamos otra variable en el GameModel a la que le llamaremos "isMyTurn"*/

        val status: String = if (game?.isGameReady == true) {

            /*11.5-Tras modificar el metodo Join, ya tendríamos el turno cargado en el GameModel,
            por lo que es horad e Cambiar el "Tu turno/Mi turno" por la variable*/
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
            GameItem()
            GameItem()
            GameItem()
        }
        Row {
            GameItem()
            GameItem()
            GameItem()
        }
        Row {
            GameItem()
            GameItem()
            GameItem()
        }
    }
}
/**************************************************************************************************/
@Composable
fun GameItem_11() {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, Accent)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "X")
    }
}
