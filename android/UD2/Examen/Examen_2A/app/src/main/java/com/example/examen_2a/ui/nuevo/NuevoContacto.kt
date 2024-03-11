package com.example.examen_2a.ui.nuevo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.examen_2a.ui.core.Routes
import com.example.examen_2a.ui.model.ContactoModel

@Composable
fun NuevoContacto(
    navigationController: NavHostController,
    nuevoContactoViewModel: NuevoContactoViewModel = hiltViewModel(),
) {

    var nombreUsuario by remember { mutableStateOf("") }
    var apellidosUsuario by remember { mutableStateOf("") }
    var tlfnUsuario by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            modifier = Modifier.size(40.dp, 40.dp),
            contentDescription = ""
        )
        Text(text = "Nuevo Contacto", fontSize = 20.sp)
        Spacer(modifier = Modifier.size(20.dp))

        OutlinedTextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = { Text("Nombre") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = apellidosUsuario,
            onValueChange = { apellidosUsuario = it },
            label = { Text("Apellidos") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = tlfnUsuario,
            onValueChange = { tlfnUsuario = it },
            label = { Text("Teléfono") },
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = {

            val nuevoContacto = ContactoModel(
                nombreUsuario = nombreUsuario,
                apellidosUsuario = apellidosUsuario,
                tlfnUsuario = tlfnUsuario.toInt(),
                codUsuario = ""
            )
            nuevoContactoViewModel.addContacto(nuevoContacto)
            navigationController.navigate(Routes.Home.route)

        }) {
            Text("Registrar Pedido")
        }
    }

}
