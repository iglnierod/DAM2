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
fun GameScreen_10(
    gameViewModel: GameViewModel_10 = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
) {

    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }

    /*10.3-Definiremos una nueva variable game que contiene el valor actual de los datos del juego (GameModel),
    lanzamos la corrutina que va a crear el juego se queda ejecutandose en sgundo plano, llama a firebase
    recupera los datos inciciales de crear la partida...y ahora los intentamos obtener antes de pintarlos en la UI.
    *
    * Al llevar el collectAsState está pendiente de la actualización( automáticamente). Cada vez que el
    * flujo gameViewModel.game emite un nuevo valor haría una recomposición en la interfaz de la aplicación
    *  con el estado más reciente del juego.
    *
    * Una vez echo esto ahora ya le podemos pasar el game, ya que, lo acabamos de meter en esa variable
    *  al Board() de tal forma que si obtenemos datos, ya podemos empezar a visualizar datos como el
    * id de la partida.
    *
    * Modificamos la cabecera de la función Board para que le pasemos el game
    * */
    val game: GameModel? by gameViewModel.game.collectAsState()
    Board_10(game)
}

/**************************************************************************************************/
@Composable
fun Board_10(game: GameModel?) { //10.3.1 modificamos para pasarl el gameModel

    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*10.3.2- Una vez que tenemos el game con datos, sustituímos por el gameId*/
        Text(text = game?.gameId.orEmpty())

        /*10.4-Ahora recuperamos el turno,necesito crear una varible isGameReady por lo que tenemos
        que modificar el GameModel
        */

        /*10.6-Ahora hacemos uso de la variable creada e implementamos la lógica... si el game está listo
        * osea está creado...solo pueden pasar dos cosas (Mi turno o el tuyo) entonces vamos a modificar
        * en el flow para poder saber eso*/

        /*10.7- No estamos utilizando el status por tanto lo vamos a sustituir por el texto que tenemos*/
        val status: String = if (game?.isGameReady == true) {
            "Tu turno/Mi turno"
        } else {
            "Esperando por el jugador 2"
        }


        /*10.8-Debería aparecer el GAMEID*/
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
fun GameItem_10() {
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
