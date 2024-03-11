package com.example.examen_2a_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class ContactEntity(
    @PrimaryKey
    val codUsuario: String,
    val nombreUsuario: String,
    val apellidosUsuario: String,
    val tlfnUsuario: Int
)