package com.example.recuperacion.ui.model

data class FoodModel (
    val id: Int = System.currentTimeMillis().hashCode(),
    var name: String,
    var description: String,
    var price: Double
) {
    override fun toString(): String {
        return "Food{name: $name, description: $description, price: $price}"
    }
}