package com.example.examen_2a.data.model

import com.example.examen_2a.ui.model.ContactoModel

data class ContactoData(
    val codUsuario: String? = null,
    val nombreUsuario: String? = null,
    val apellidosUsuario: String? = null,
    val tlfnUsuario: Int? = null
){
    fun toModel(): ContactoModel {
        return ContactoModel(
            codUsuario = codUsuario ?: "",
            nombreUsuario = nombreUsuario ?: "",
            apellidosUsuario = apellidosUsuario ?: "",
            tlfnUsuario = tlfnUsuario ?: 0
        )
    }
}
