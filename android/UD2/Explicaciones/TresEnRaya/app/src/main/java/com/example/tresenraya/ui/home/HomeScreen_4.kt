package com.example.tresenraya.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


/*4.10 creamos la función lambda navigateToGame, la función lambda se pasa como argumento a la función
HomeScreen y se usa para navegar a la pantalla del juego cuando se cumple una condición específica.
Esta función lambda permite definir código que se puede pasar como argumento a otras funciones y reutilizarlo
donde queramos*/

@Composable
fun HomeScreen_4(
    homeViewModel: HomeViewModel_4 = hiltViewModel(),
    navigateToGame: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ) {
        Spacer(modifier = Modifier.weight(2f))

        /*4.12-Modificamos la llamada, pasándole la nueva función lambda*/
        CreateGame_4(onCreateGame = {navigateToGame()})
        Spacer(modifier = Modifier.weight(1f))
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp))
        Spacer(modifier = Modifier.weight(1f))
        JoinGame_4(onJoinGame = {id: String-> homeViewModel.onJoinGame_4(id) })
        Spacer(modifier = Modifier.weight(2f))
    }
}

/**************************************************************************************************/
@Composable
fun CreateGame_4(onCreateGame: () -> Unit) {
    Button(onClick = { onCreateGame() }) {
        Text(text = "Create game")
    }
}

/**************************************************************************************************/
@Composable
fun JoinGame_4(onJoinGame: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    TextField(value = text, onValueChange = { text = it})
    
    Button(onClick = { onJoinGame(text) }, enabled = text.isNotEmpty()) {
        Text(text = "Join Game")
    }
}