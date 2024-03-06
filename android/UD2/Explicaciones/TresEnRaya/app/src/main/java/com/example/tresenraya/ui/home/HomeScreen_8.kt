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


@Composable
fun HomeScreen_8(
    homeViewModel: HomeViewModel_8 = hiltViewModel(),

    /*8.2-Reutilizo la función lambda navigateToGame y le paso los parámetros que necesito*/
    navigateToGame: (String, String, Boolean) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ) {
        Spacer(modifier = Modifier.weight(2f))
        CreateGame_8(onCreateGame = {homeViewModel.onCreateGame_8(navigateToGame)})
        Spacer(modifier = Modifier.weight(1f))
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp))
        Spacer(modifier = Modifier.weight(1f))

        /*8.11-Modificar la llamada del JoinGame con los parámetros actualizados*/
        JoinGame_8(onJoinGame = {gameId: String -> homeViewModel.onJoinGame_8(gameId, navigateToGame) })
        Spacer(modifier = Modifier.weight(2f))
    }
}


@Composable
fun CreateGame_8(onCreateGame: () -> Unit) {
    Button(onClick = { onCreateGame }) {
        Text(text = "Create game")
    }
}

@Composable
fun JoinGame_8(onJoinGame: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    TextField(value = text, onValueChange = { text = it})
    
    Button(onClick = { onJoinGame(text) }, enabled = text.isNotEmpty()) {
        Text(text = "Join Game")
    }
}