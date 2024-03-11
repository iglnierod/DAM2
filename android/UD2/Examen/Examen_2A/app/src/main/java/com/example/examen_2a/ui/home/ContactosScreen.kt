package com.example.examen_2a.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.examen_2a.ui.core.Routes
import com.example.examen_2a.ui.model.ContactoModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactosScreen(
    navigationController: NavHostController,
    contactosViewModel: ContactosViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        contactosViewModel.getDatos()
    }

    val contactos: ContactoModel? by contactosViewModel.contactos.collectAsState()
    Log.i("contactosIsNull:",(contactos == null).toString())
    contactos?.let { Log.i("contactos:", it.nombreUsuario) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigationController.navigate(Routes.Nuevo.route) },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Agregar nuevo contacto"
                    )
                }
            )
        }
    ) {it
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ) {
            contactos?.let { ContactoItem(it) }
        }
    }

}

/**************************************************************************************************/
@Composable
fun ContactoItem(contactos: ContactoModel?) {

    /*Ejercicio 4: Modifica el ContactosScreen.kt para que cuando mantengamos pulsado el card del
    contacto elimine el contacto. (1.5 Puntos)
     */

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
    ) {
        if (contactos != null) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp), verticalArrangement = Arrangement.Center ) {
                Text("Nombre: ${contactos.nombreUsuario}")
                Text("Apellidos: ${contactos.apellidosUsuario}")
                Text("Teléfono: ${contactos.tlfnUsuario}")
            }
        } else {
            Text("No hay datos disponibles")
        }
    }
}


