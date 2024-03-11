package com.example.tresenraya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tresenraya.ui.core.ContentWrapper
import com.example.tresenraya.ui.theme.TresEnRayaTheme
import dagger.hilt.android.AndroidEntryPoint


// Listado de partidas
    // board : List<String> = 9 posiciones
    // gameId:String
        //Player1
            //playerId
            //playerType
        //Player2
        //PlayerTurn

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navigationController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TresEnRayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigationController = rememberNavController()
                    ContentWrapper(navigationController = navigationController)
                }
            }
        }
    }
}