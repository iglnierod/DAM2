package com.example.recuperacion.data

import com.example.recuperacion.ui.model.FoodModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(private val foodDao: FoodDao) {
    suspend fun add(foodModel: FoodModel) {
        foodDao.addFood(
            FoodEntity(
                foodModel.id, foodModel.name, foodModel.description, foodModel.price
            )
        )
    }

    val foods: Flow<List<FoodModel>> =
        foodDao.getFoods().map { items ->
            items.map {
                FoodModel(it.id, it.name, it.description, it.price)
            }
        }
}