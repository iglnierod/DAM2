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

@AndroidEntryPoint
class MainActivity_4 : ComponentActivity() {

    /*4.7-Definimos una variable navigationController privada de tipo NavHostController. La variable se
    declara como lateinit, lo que significa que no se inicializa al declararla.*/
    private lateinit var navigationController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TresEnRayaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*4.8-Una vez inicializada la variable, necesito obtener la posición actual del
                     mainAntivity, punto de partida de la app. Este valor lo obtengo con la función
                     rememberNavController().

                     Esto significa que la instancia de NavController se mantendrá viva mientras la
                     composición esté activa, y se eliminará cuando la composición se deseche.
                     */

                    navigationController = rememberNavController()


                    /* 4.6-Llamamos al ContentWrapper pero cómo toda APP con Compose necesita saber
                     en que Screen está, por lo que tendremos que pasarle un elemento
                     navigationController que se va ir actualizando en función del layout en el que esté
                     */
                    ContentWrapper(navigationController = navigationController)
                }
            }
        }
    }
}