package com.example.recuperacion.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods_table")
data class FoodEntity(
    @PrimaryKey
    val id: Int,
    var name: String,
    var description: String,
    var price: Double
)
