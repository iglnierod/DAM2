package com.example.recuperacion.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.recuperacion.R
import com.example.recuperacion.ui.model.FoodModel
import androidx.compose.runtime.produceState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.recuperacion.Food

/**************************************************************************************************/
/***************************************** PRINCIPAL **********************************************/
/**************************************************************************************************/

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun MainContent(foodViewModel: FoodViewModel) {

    val showDialog: Boolean by foodViewModel.showDialog.observeAsState(false)

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<FoodUiState>(
        initialValue = FoodUiState.Loading,
        key1 = lifecycle,
        key2 = foodViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            foodViewModel.uiState.collect { value = it }
        }
    }

    val foods: List<FoodModel> = foodViewModel.getFoods(uiState)

    Log.i("FoodsList", foods.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        //FoodList(foodViewModel = foodViewModel)
        FoodList(foods = foods, foodViewModel = foodViewModel)
        FabDialog(Modifier.align(Alignment.BottomEnd), foodViewModel)
        AddFoodsDialog(
            showDialog,
            onDismiss = { foodViewModel.onDialogClose() },
            onFoodAdded = { name, description, price ->
                foodViewModel.onFoodCreated(
                    name,
                    description,
                    price
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodsDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onFoodAdded: (String, String, Double) -> Unit
) {
    var myName by remember { mutableStateOf("") }
    var myDescription by remember { mutableStateOf("") }
    var myPrice by remember { mutableStateOf("") }

    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(18.dp)
            ) {
                Text(
                    text = "Añadir comida",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myName,
                    onValueChange = { myName = it },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(text = "Nombre")
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myDescription,
                    onValueChange = { myDescription = it },
                    singleLine = false,
                    maxLines = 3,
                    placeholder = {
                        Text(text = "Descripción")
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myPrice,
                    onValueChange = { myPrice = it },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(text = "Precio")
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    Log.i("Button:", "myName:$myName,myDescription:$myDescription,myPrice:$myPrice")
                    onFoodAdded(myName, myDescription, myPrice.toDouble())
                    myName = ""
                    myDescription = ""
                    myPrice = ""
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir comida")
                }
            }
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, foodViewModel: FoodViewModel) {
    FloatingActionButton(onClick = {
        foodViewModel.onShowDialogClick()
    }, modifier = modifier) {
        Icon(Icons.Filled.Add, contentDescription = "Añadir")
    }
}

/**************************************************************************************************/
/********************************** LISTADO TODAS LAS COMIDAS *************************************/
/**************************************************************************************************/
/*
* [1 PUNTOS]
* Ejercicio 1: Partiendo del código proporcionado, haz que al listar la cómida se puedan visualizar
* elementos infinitos y que podamos subir y bajar en el menu de comidas.
* */

@Composable
fun FoodList(foods: List<FoodModel>, foodViewModel: FoodViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        MenuBusqueda()
        LazyColumn {
            itemsIndexed(foods) { _, food ->
                FoodItem(
                    foodName = food.name,
                    foodDescription = food.description,
                    foodPrice = food.price.toString()
                )
            }
        }
    }
}

/**************************************************************************************************/
/************************************** FORMATO CADA ITEM ******************************************/
/**************************************************************************************************/
/*
* [3,5 PUNTOS]
* Ejercicio 2: Crea una CARD cómo la mostrada en la imagen para ir listando cada unos de los elemen
* tos que tenemos disponibles en nuestra carta.
* */

@Composable
fun FoodItem(
    foodName: String, foodDescription: String, foodPrice: String /*,
foodImagen: Int*/
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF206A5D),
            contentColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.comida_1),
                contentDescription = "comida",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(0.dp, Color.Transparent, CircleShape),
            )
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = foodName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = foodDescription,
                    modifier = Modifier.padding(0.dp, 5.dp),
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "$foodPrice €", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.size(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(250.dp, 35.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(Modifier.size(10.dp))
                    Text(text = "Comprar")
                }
            }
        }
    }
}

/**************************************************************************************************/
/*********************************** REALIZA EL MENU DE BUSQUEDA **********************************/
/**************************************************************************************************/
/*
* [2 PUNTOS]
* Ejercicio 3: Crea un buscador que permita al usuario buscar por nombre las comidas disponibles en
* la carta.
* */

@Composable
fun MenuBusqueda() {
    Column {

    }
}
/**************************************************************************************************/
/************************************** AÑADIR AL CARRITO *****************************************/
/**************************************************************************************************/
/*
* [2,5 PUNTO]
* Ejercicio 4: Crea un DIALOG que cuando clickemos en el botón de "Añadir a la cesta" nos lleve a un
* dialogo con una imagen del Xokas y que en la pantalla diga "Listo!" cómo se aprecia en el video.
* El dialogo debe tener el siguiente comportamiento: al pulsar el botón atrás en android o clickar
* fuera del dialogo debemos volver a la ventana anterior.
* */
@Composable
fun AddCart() {

}
/**************************************************************************************************/
/******************************************* TOP APP BAR ******************************************/
/**************************************************************************************************/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { ImageLogo() },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.Black
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "KnowEats",
                modifier = Modifier.size(35.dp)
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Cesta de la compra",
                    tint = Color(0xFF206A5D),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    )
}

/**************************************************************************************************/
/************************************ LOGO CENTRAL DE LA TOPAPPBAR *********************************/
/**************************************************************************************************/
/*
* [1 PUNTO]
* Ejercicio 5: Modifica la siguiente función para que la imagen "Knoweats!" quede centrada entre el
* logo de la compañía y el logo del carro de compra
* */
@Composable
fun ImageLogo() {
    Row() {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "KnowEats")
    }

}