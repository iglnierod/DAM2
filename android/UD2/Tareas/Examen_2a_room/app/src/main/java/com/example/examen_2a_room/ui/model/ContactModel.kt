package com.example.examen_2a_room.ui.model

data class ContactModel(
    val codUsuario: String = System.currentTimeMillis().hashCode().toString(),
    val nombreUsuario: String,
    val apellidosUsuario: String,
    val tlfnUsuario: Int
) {
    override fun toString(): String {
        return "Contact{cod: $codUsuario, nombre: $nombreUsuario, apellidos: $apellidosUsuario, tlf: $tlfnUsuario}"
    }
}