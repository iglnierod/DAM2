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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tresenraya.ui.theme.Accent


@Composable
fun GameScreen_(
    gameViewModel: GameViewModel_9 = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
) {
    /*9.1- Ya tenemos los campos necesarios para unirnos al partido,ahora nos unimos a una partida
    con la función LaunchedEffect que obtiene la información de la partida solicitada en segundo plano,
    la enganchamos con el gameViewModel para que pase los datos y pinte el tablero*/

    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }
    Board_9()
}


@Composable
fun Board_9() {
    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "IdDePartida")
        Text(text = "ESPERA/MITURNO/TUTURNO")

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

@Composable
fun GameItem_9() {
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
