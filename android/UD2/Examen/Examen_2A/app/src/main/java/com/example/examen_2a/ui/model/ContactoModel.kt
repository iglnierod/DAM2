package com.example.examen_2a.ui.model

import com.example.examen_2a.data.model.ContactoData

data class ContactoModel(
    val codUsuario: String,
    val nombreUsuario: String,
    val apellidosUsuario: String,
    val tlfnUsuario: Int
){
    fun toData(): ContactoData {
        return ContactoData(
            codUsuario = codUsuario,
            nombreUsuario = nombreUsuario,
            apellidosUsuario = apellidosUsuario,
            tlfnUsuario = tlfnUsuario
        )
    }
}
