package com.example.tresenraya.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tresenraya.ui.theme.Accent


/* 3.0-Tenemos que crear un tres en raya, tenemos que pensar que hai que mostrar más de una cosa, porque
* si entro a la partida no sé si me toca a mi, si ya tengo rival por lo que empieza a ser confuso, por
* lo tanto lo principal es poner unos textos arriba que me den información
* */


@Composable
fun GameScreen(gameViewModel: GameViewModel_3 = hiltViewModel()) {
    /*3.7-Modificamos la cabecera para pasarle el viewmodel que se encarga de obtener una instancia
    de la clase GameViewModel y de inyectarle las dependencias que necesita. Esto significa que no
    tienes que preocuparte por cómo crear o configurar la clase GameViewModel*/

    Board()
}

/**************************************************************************************************/
/*3.1-Creamos el tablero de la partida*/
@Composable
fun Board() {

    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*3.2-Lo primero que vamos a mostrar es el ID de la partida, para que un usuario se nos pueda añadir
        * a la partida*/
        Text(text = "IdDePartida")

        /*3.3-Luego debería decir el estado de la partida: En espera/Mi turno/Su turno */
        Text(text = "ESPERA/MITURNO/TUTURNO")

        /*3.5-Creamos los huecos para la X o el O y luego las lineas*/
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
/*3.4-Creamos cada una de las celdas*/
@Composable
fun GameItem() {
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
