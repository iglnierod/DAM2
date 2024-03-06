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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tresenraya.ui.theme.Accent



@Composable
fun GameScreen_8(
    /*8.9- MOdificamos lo que se está pasando al GameScreen(), mantenemos el gameViewModel y añadimos
    los nuevos valores gameId, userId y owner*/

    gameViewModel: GameViewModel_3 = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean

) {
    Board_8()
}


@Composable
fun Board_8() {

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
fun GameItem_8() {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, Accent)),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.Text(text = "X")
    }
}
