package com.example.recuperacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.recuperacion.ui.FoodViewModel
import com.example.recuperacion.ui.MainContent

import com.example.recuperacion.ui.theme.RecuperacionTheme
import dagger.hilt.android.AndroidEntryPoint


/**************************************************************************************************/
/*****************************************  D A T O S *********************************************/
/**************************************************************************************************/
data class Food(
    val name: String,
    val description: String,
    val price: String,
    val imagen: Int
)

val foods = listOf(
    Food(
        name = "Pollo al curry con arroz",
        description = "Pollo tierno en salsa de curry, con arroz y verduras frescas. Una delicia inspirada en la tradición asiática.",
        price = "7.49€",
        imagen = R.drawable.comida_1
    ),
    Food(
        name = "Lasaña de Pollo",
        description = "Lasaña de pollo a base de queso y carne troceada de pollo de optima calidad.(450g)",
        price = "7.15€",
        imagen = R.drawable.comida_2
    ),
    Food(
        name = "Salmón en salsa de puerros",
        description = "Salmón cocinado a la plancha lentamente, con una deliciosa salsa de puerros.(450g)",
        price = "8.95€",
        imagen = R.drawable.comida_3
    ),
    Food(
        name = "Fabada asturiana",
        description = "Plato tradiccional de Asturias.(450g)",
        price = "7.15€",
        imagen = R.drawable.comida_4
    ),
    Food(
        name = "Secreto al monastrell",
        description = "Secreto de cerdo con patatas y champiónes.(450g)",
        price = "9.30€",
        imagen = R.drawable.comida_5
    )
)

/**************************************************************************************************/
/*****************************************  M A I N ***********************************************/
/**************************************************************************************************/
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val foodViewModel: FoodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecuperacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MainContent(foods)
                    MainContent(foodViewModel)
                }
            }
        }
    }
}