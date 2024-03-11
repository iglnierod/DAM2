package com.example.recuperacion.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.examen_2a_room.ui.ContactUiState
import com.example.examen_2a_room.ui.ContactViewModel
import com.example.examen_2a_room.ui.model.ContactModel

/**************************************************************************************************/
/***************************************** PRINCIPAL **********************************************/
/**************************************************************************************************/

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun MainContent(contactViewModel: ContactViewModel) {

    val showDialog: Boolean by contactViewModel.showDialog.observeAsState(false)

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<ContactUiState>(
        initialValue = ContactUiState.Loading,
        key1 = lifecycle,
        key2 = contactViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            contactViewModel.uiState.collect { value = it }
        }
    }

    val contacts: List<ContactModel> = contactViewModel.getContacts(uiState)

    Log.i("FoodsList", contacts.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        //FoodList(foodViewModel = foodViewModel)
        FoodList(contacts = contacts, contactViewModel = contactViewModel)
        FabDialog(Modifier.align(Alignment.BottomEnd), contactViewModel)
        AddFoodsDialog(
            showDialog,
            onDismiss = { contactViewModel.onDialogClose() },
            onFoodAdded = { nombre, apellidos, tlf ->
                contactViewModel.onContactCreated(
                    nombre, apellidos, tlf
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
    onFoodAdded: (String, String, String) -> Unit
) {
    var myName by remember { mutableStateOf("") }
    var mySurname by remember { mutableStateOf("") }
    var myTlf by remember { mutableStateOf("") }

    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(18.dp)
            ) {
                Text(
                    text = "Añadir contacto",
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
                    value = mySurname,
                    onValueChange = { mySurname = it },
                    singleLine = false,
                    maxLines = 3,
                    placeholder = {
                        Text(text = "Apellidos")
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTlf,
                    onValueChange = { myTlf = it },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(text = "Telefono")
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    Log.i("Button:", "myName:$myName,myDescription:$mySurname,myPrice:$myTlf")
                    onFoodAdded(myName, mySurname, myTlf)
                    myName = ""
                    mySurname = ""
                    myTlf = ""
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir comida")
                }
            }
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, contactViewModel: ContactViewModel) {
    FloatingActionButton(onClick = {
        contactViewModel.onShowDialogClick()
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
fun FoodList(contacts: List<ContactModel>, contactViewModel: ContactViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        LazyColumn {
            itemsIndexed(contacts) { _, user ->
                FoodItem(contactModel = user, contactViewModel = contactViewModel)
            }
        }
    }
}

/**************************************************************************************************/
/************************************** FORMATO CADA ITEM ******************************************/
/**************************************************************************************************/
@Composable
fun FoodItem(
    contactModel: ContactModel,
    contactViewModel: ContactViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { contactViewModel.onItemRemoved(contactModel) }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF206A5D),
            contentColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Nombre: ${contactModel.nombreUsuario}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Apellidos: ${contactModel.apellidosUsuario}",
                    modifier = Modifier.padding(0.dp, 5.dp),
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "Número: ${contactModel.tlfnUsuario}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.size(10.dp))
            }
        }
    }
}

