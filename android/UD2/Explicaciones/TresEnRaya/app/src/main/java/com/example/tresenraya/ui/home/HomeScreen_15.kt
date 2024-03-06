package com.example.tresenraya.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tresenraya.R
import com.example.tresenraya.ui.theme.Accent
import com.example.tresenraya.ui.theme.Background
import com.example.tresenraya.ui.theme.Orange1
import com.example.tresenraya.ui.theme.Orange2


@Composable
fun HomeScreen_15(
    homeViewModel: HomeViewModel_8 = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {

    /*15.1- LLegados este punto vamos a ejecutar la APP...veremos que la APP está un poco sosa...
    * Vamos a empezar por tocar los colores dentro de la carpeta THEME
    *
    * ------Color.kt*/
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //15.3- Añadimo .background(Background) y probamos
        modifier = Modifier
            .fillMaxSize()
            .background(Background)

    ) {
        //15.4.1 Añadimos el Header
        Header_15()
        //15.5.2 Añadimos el Body con sus respectivos parámetros
        Body_15(
            onCreateGame = { homeViewModel.onCreateGame_8(navigateToGame) },
            onJoinGame = { gameId -> homeViewModel.onJoinGame_8(gameId, navigateToGame) }
        )

    }
}

/*15.4 Vamos a meterle una cabecera...algo bonito como entrada al juego....definimos un nuevo composable*/
@Composable
fun Header_15() {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(12.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(2.dp, Orange1, RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            )
        }
        Text(text = "Firebase",fontSize = 32.sp,color = Orange1,fontWeight = FontWeight.Bold)
        Text(text = "3 en raya",fontSize = 32.sp,color = Orange2,fontWeight = FontWeight.Bold)
    }

}
/***************************************************************************************************/
/*15.5-Creamos el composable para el Body, simplemente hacemos una card que tenga un switch que por
* defecto nos muestre crear partida y cuando lo seleccionamos este cambia a unirse a partida. Aquí
* llamaremos al createGame y al JoinGame, la cuestión es que no debemos pasar directamente el homeviewModel
* o el GameViewModel. entonces lo ajustamos pasando la lambda*/

@Composable
fun Body_15(onCreateGame: () -> Unit, onJoinGame: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        backgroundColor = Background,
        border = BorderStroke(2.dp, Orange1),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            var createGame by remember { mutableStateOf(true) }
            Switch(
                checked = createGame,
                onCheckedChange = { createGame = it },
                colors = SwitchDefaults.colors(checkedThumbColor = Orange2)
            )
            /*15.5.1-Creo una pequeña animación*/
            AnimatedContent(targetState = createGame, label = "") {
                when (it) {
                    true -> CreateGame_15(onCreateGame)
                    //true -> CreateGame(onCreateGame = {homeViewModel.onCreateGame_8(navigateToGame)})
                    false -> JoinGame_15(onJoinGame)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
/***************************************************************************************************/
/*15.6-Modificamos los colores del botón con el color personalizado creado en el theme/color.kt*/

@Composable
fun CreateGame_15(onCreateGame: () -> Unit) {
    Button(
        onClick = { onCreateGame },
        colors = ButtonDefaults.buttonColors(backgroundColor = Orange1)
    ) {
        Text(text = "Create game")
    }
}

/***************************************************************************************************/
/*15.7-Modificamos el TextField por un OutlinedTextField y cambiamos los colores para utilizar los
* definidos*/

@Composable
fun JoinGame_15(onJoinGame: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Accent,
                focusedBorderColor = Orange1,
                unfocusedBorderColor = Accent, cursorColor = Orange1
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onJoinGame(text) },
            enabled = text.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Orange1)
        ) {
            Text(text = "Join to game", color = Accent)
        }
    }
}