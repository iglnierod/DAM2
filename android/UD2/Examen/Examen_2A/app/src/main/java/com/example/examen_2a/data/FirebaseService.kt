package com.example.examen_2a.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.examen_2a.data.model.ContactoData
import com.example.examen_2a.ui.model.ContactoModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class FirebaseService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        const val PATH = "contactos"
    }

    fun addContacto(pedido: ContactoModel) {
        val contactoRef = reference.child(PATH).push()
        val codUsuario = contactoRef.key
        val newPedido = pedido.copy(codUsuario = codUsuario.orEmpty())
        contactoRef.setValue(newPedido)
    }

    /**********************************************************************************************/
    /*Ejercicio 2: Crear una función obtenerContactos que obtenga los datos que guardamos en realtime database
    (4 puntos)*/
    fun obtenerContactos(): Flow<ContactoModel?> {
        return reference.database.reference.child(PATH).snapshots.map { value: DataSnapshot ->
            value.getValue(ContactoData::class.java)?.toModel()
        }
    }

    /**********************************************************************************************/
    /*Ejercicio 3: Crea una función que permita eliminar el contacto (3 puntos)*/
    fun deleteContacto() {

    }

}

